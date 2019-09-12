/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig
public class Style extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher = request.getRequestDispatcher("style.jsp");
        Base64.Encoder encoder = Base64.getEncoder();

        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();

        String[] filter = request.getParameterValues("style_apply");
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;

        while ((len = fileContent.read(buffer, 0, buffer.length)) != -1) {
            os.write(buffer, 0, len);
        }

        byte[] img = os.toByteArray();

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(img));

        String[] file_details = fileName.split("\\.");
        String extension = file_details[1];
        String file_name = file_details[0];

        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();

        try {

            ImageEffect ie = new ImageEffect();

            for (String f : filter) {

                if (f.equals("grayscale")) {
                    byte[] org_img = ie.get_byte_array(ie.gray_scale(image), extension);
                    String gray = "data:image/png;base64," + encoder.encodeToString(org_img);
                    list.add(gray);
                    name.add("GrayScale");
                } else if (f.equals("negative")) {
                    encoder = Base64.getEncoder();
                    image = ImageIO.read(new ByteArrayInputStream(img));
                    byte[] negative_img = ie.get_byte_array(ie.negative(image), extension);
                    String negative = "data:image/png;base64," + encoder.encodeToString(negative_img);
                    list.add(negative);
                    name.add("Negative");
                } else if (f.equals("sepia")) {
                    encoder = Base64.getEncoder();
                    image = ImageIO.read(new ByteArrayInputStream(img));
                    byte[] sepia_img = ie.get_byte_array(ie.sepia(image), extension);
                    String sepia = "data:image/png;base64," + encoder.encodeToString(sepia_img);
                    list.add(sepia);
                    name.add("Sepia");
                } else if (f.equals("sharpen")) {
                    encoder = Base64.getEncoder();
                    image = ImageIO.read(new ByteArrayInputStream(img));
                    byte[] sharpen_img = ie.get_byte_array(ie.sharpen(image), extension);
                    String sharpen = "data:image/png;base64," + encoder.encodeToString(sharpen_img);
                    list.add(sharpen);
                    name.add("Sharpen");
                } else if (f.equals("b&w")) {
                    encoder = Base64.getEncoder();
                    image = ImageIO.read(new ByteArrayInputStream(img));
                    byte[] thresh_img = ie.get_byte_array(ie.threshold(image), extension);
                    String thresh = "data:image/png;base64," + encoder.encodeToString(thresh_img);
                    list.add(thresh);
                    name.add("B&W");
                } else if (f.equals("borderline")) {
                    encoder = Base64.getEncoder();
                    image = ImageIO.read(new ByteArrayInputStream(img));
                    byte[] edge_img = ie.get_byte_array(ie.edge(image), extension);
                    String edge = "data:image/png;base64," + encoder.encodeToString(edge_img);
                    list.add(edge);
                    name.add("Borderline");
                } else if (f.equals("smooth")) {
                    encoder = Base64.getEncoder();
                    image = ImageIO.read(new ByteArrayInputStream(img));
                    byte[] blur_img = ie.get_byte_array(ie.blur(image), extension);
                    String blur = "data:image/png;base64," + encoder.encodeToString(blur_img);
                    list.add(blur);
                    name.add("Smooth");
                } else if (f.equals("hue")) {
                    encoder = Base64.getEncoder();
                    image = ImageIO.read(new ByteArrayInputStream(img));
                    byte[] hue_img = ie.get_byte_array(ie.hue(image), extension);
                    String hue = "data:image/png;base64," + encoder.encodeToString(hue_img);
                    list.add(hue);
                    name.add("Hue");
                } else if (f.equals("salavi")) {
                    encoder = Base64.getEncoder();
                    image = ImageIO.read(new ByteArrayInputStream(img));
                    byte[] custom_img = ie.get_byte_array(ie.custom(image), extension);
                    String custom = "data:image/png;base64," + encoder.encodeToString(custom_img);
                    list.add(custom);
                    name.add("Salavi");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("image_name", name);
        request.setAttribute("image_src", list); // set your String value in the attribute
        
        
        
        int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        
        if(userid != -1)
        {
            try {
                insert_into_db(request, list, name);
            } catch (Exception ex) {
                Logger.getLogger(Style.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        dispatcher.forward(request, response);

    }

    private void insert_into_db(HttpServletRequest request, ArrayList<String> list,  ArrayList<String> name ) throws Exception {

        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

        //creating connection with the database 
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/styler_db", "dbuser", "12345");

        int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        
        for (int i = 0 ; i < list.size(); i++)
        {
            PreparedStatement ps = con.prepareStatement("insert into DBUSER.IMAGE_TABLE (USER_ID, IMAGE_NAME, IMAGE_DATA)"
                + "values(?,?,?)");

            ps.setString(1, Integer.toString(userid));
            ps.setString(2, name.get(i));
            
            Blob blob = con.createBlob();
            blob.setBytes(1, list.get(i).getBytes());
            
            ps.setBlob(3, blob);
            
            
            int j = ps.executeUpdate();
            
        }
      

    }

}
