package com.servlet.register;

import java.io.IOException;
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

@WebServlet("/reset")
public class reset extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("account_no");
        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String conformPassword = request.getParameter("confirm_password");
        System.out.println(newPassword);

        System.out.println(conformPassword);

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Establish connection to the database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "changeme");

            // Check if account number exists and old password matches
            String query = "SELECT * FROM customer WHERE account_no = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, accountNumber);
            stmt.setString(2, oldPassword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Account number and old password match
                // Update the password
            	if(newPassword.equals(conformPassword)) {
                String updateQuery = "UPDATE customer SET password = ? WHERE account_no = ?";
                stmt = conn.prepareStatement(updateQuery);
                stmt.setString(1, newPassword);
                stmt.setString(2, accountNumber);
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    // Password updated successfully
                    response.sendRedirect("password_reset_success.jsp");
                } else {
                    // Failed to update password
                    response.sendRedirect("password_reset_failure.jsp");
                }
                }
            	else {response.getWriter().println("<script>alert('New password and Conform password is not same please enter correctly');window.location.href='reset.html';</script>");
            	}
            	}
            else {
                // Account number or old password does not match
            	response.getWriter().println("<script>alert('Invalid Customer  Name and Password');window.location.href='reset.html';</script>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            // Close PreparedStatement and Connection
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
