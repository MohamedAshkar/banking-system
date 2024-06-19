package com.servlet.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Imports

@WebServlet("/modifyemail")
public class ModifyEmailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("account_no");
        String newEmail = request.getParameter("email");

        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://localhost:3306/bank";
        String username = "root";
        String password = "changeme";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE customer SET email = ? WHERE account_no = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, newEmail);
                stmt.setString(2, accountNumber);

                // Check if any rows were affected by the update
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Successful modification
                    response.sendRedirect("modify_success.html");
                } else {
                    // No rows were affected, handle accordingly (e.g., display a message)
                    response.sendRedirect("modify_failure.html");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            String errorMessage = "Error: " + ex.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Error: " + e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
