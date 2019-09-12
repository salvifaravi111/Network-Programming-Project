
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
import java.sql.Statement;
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
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/styler_db", "dbuser", "12345");
           
            PreparedStatement ps = con.prepareStatement("SELECT * from DBUSER.USER_TABLE WHERE EMAIL=? and"
                    + " password=?");

           
            ps.setString(1, email);
            ps.setString(2, password);
            
           

            ResultSet rs = ps.executeQuery();
           
            if (rs.next()) {
                int userid = rs.getInt(1);
                String username = rs.getString("username");
                request.getSession().setAttribute("userid",userid);
                request.getSession().setAttribute("username",username);
                response.sendRedirect("index.jsp");
                
            }
            else
            {
                request.getSession().setAttribute("userid",-1);
                request.getSession().setAttribute("username","");
                out.println("Not Found");
                response.sendRedirect("index.jsp");
            }
            

        } catch (Exception se) {
            se.printStackTrace();
        }

    }
}
