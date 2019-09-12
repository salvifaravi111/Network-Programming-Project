
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.Base64;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import org.jboss.weld.servlet.SessionHolder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sifat Ahmed
 */
public class UserImage extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("style.jsp");
        int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());

        try {

            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/styler_db", "dbuser", "12345");

            PreparedStatement ps = con.prepareStatement("SELECT * from DBUSER.IMAGE_TABLE WHERE USER_ID=?");

            ps.setString(1, Integer.toString(userid));

            ResultSet rs = ps.executeQuery();

            ArrayList<String> list = new ArrayList<String>();
            ArrayList<String> name = new ArrayList<String>();

            while (rs.next()) {

                Blob blob = rs.getBlob("IMAGE_DATA");
                byte[] image_data = blob.getBytes(1, (int) blob.length());
                list.add(new String(image_data));
                name.add(rs.getString("IMAGE_NAME"));

            }
            
            Collections.reverse(list);
            Collections.reverse(name);
            

            request.setAttribute("image_name", name);
            request.setAttribute("image_src", list);
            
            dispatcher.forward(request, response);

        } catch (Exception se) {
            se.printStackTrace();
        }

    }
}
