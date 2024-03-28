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

@WebServlet("/back")
public class back extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get PrintWriter
        PrintWriter out = res.getWriter();
        // Set Content type
        res.setContentType("text/html");
        // Read the form values
        String account_no = req.getParameter("account_no");

        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Create the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///bank", "root", "changeme");
                PreparedStatement ps = con.prepareStatement("select * from customer where account_no=?");) {
            // Set the values
            ps.setString(1, account_no);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	String balance = rs.getString("balance");
            	String account_type = rs.getString("acc_type");
            	String name = rs.getString("name");
            	req.setAttribute("account_no", account_no);
                req.setAttribute("account_type", account_type);
                req.setAttribute("balance", balance);
                req.setAttribute("name", name);
                
                // Forward the request to the showacc.jsp page
                req.getRequestDispatcher("customerdashbord.jsp").forward(req, res);
            } else {
                // Send error message to demo.html
                req.setAttribute("message", "Login Failed. Invalid username or password.");
                req.getRequestDispatcher("loginpage.html").forward(req, res);
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
