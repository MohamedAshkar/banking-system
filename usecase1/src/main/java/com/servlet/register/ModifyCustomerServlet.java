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

@WebServlet("/modifyCustomer")
public class ModifyCustomerServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form submission
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobile_no");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Prepare SQL statement to update customer details
            String updateQuery = "UPDATE customer SET address=?, mobile_no=? WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, mobileNo);
            preparedStatement.setString(3, email);

            // Execute the update operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // If rows were affected, display success message
                response.getWriter().println("Customer details modified successfully.");
            } else {
                // If no rows were affected, display error message
                response.getWriter().println("No customer found with the provided email.");
            }
        } catch (SQLException e) {
            // Catch and handle SQL exceptions
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}