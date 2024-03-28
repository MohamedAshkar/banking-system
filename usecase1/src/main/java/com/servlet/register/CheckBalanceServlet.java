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

@WebServlet("/checkbalance")
public class CheckBalanceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNumber = request.getParameter("account_no");
        PrintWriter out = response.getWriter();
        Connection connection = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "changeme");

            // Prepare SQL statement for retrieving balance
            String sql = "SELECT balance FROM customer WHERE account_no = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();

            // Check if balance is retrieved successfully
            if (resultSet.next()) {
                int balance = resultSet.getInt("balance");
                out.print(balance);
            } else {
                out.print(0); // Account not found
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.print("Error: " + e.getMessage());
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
