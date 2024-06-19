package com.servlet.register;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminlogin")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get PrintWriter
        PrintWriter out = res.getWriter();
        // Set Content type
        res.setContentType("text/html");
        // Read the form values
        String name = req.getParameter("username");
        String password = req.getParameter("password");

        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
     // Create the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///traking", "root", "changeme");
                PreparedStatement ps = con.prepareStatement("select * from admin where name=? and password=?");) {
            // Set the values
            ps.setString(1, name);
            ps.setString(2, password);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                res.sendRedirect("admindashboard.html");
            } else {
                // Send error message to demo.html
            	res.getWriter().println("<script>alert('Invalid Customer  Name and Password');window.location.href='adminlogin.html';</script>");
            }
        } catch (SQLException se) {
            out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            out.println(e.getMessage());
            e.printStackTrace();
        }

        // Close the stream
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}