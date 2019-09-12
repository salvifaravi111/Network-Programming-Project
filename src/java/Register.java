
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sifat Ahmed
 */
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/styler_db", "dbuser", "12345");
           
            PreparedStatement ps = con.prepareStatement("insert into DBUSER.USER_TABLE (USERNAME, EMAIL, PASSWORD)"
                    + "values(?,?,?)", new String[]{"USER_ID"});

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            
            int i = ps.executeUpdate();
          

            if (i > 0) 
                out.println("You are sucessfully registered");
            

            ResultSet rs = ps.getGeneratedKeys();
           
            if (rs.next()) {
                int userid = rs.getInt(1);
                request.getSession().setAttribute("userid",userid);
                request.getSession().setAttribute("username",username);
                
            }

        } catch (Exception se) {
            se.printStackTrace();
        }

    }
}
