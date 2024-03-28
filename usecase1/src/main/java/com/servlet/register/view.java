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

@WebServlet("/view")
public class view extends HttpServlet {
    private static final String SELECT_USER_QUERY = "SELECT * FROM customer WHERE account_no=?";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get the account number from the form
        String accountNumber = request.getParameter("account_no");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "changeme")) {
            // Prepare the SQL statement
            PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_QUERY);
            preparedStatement.setString(1, accountNumber);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if user exists
            if (resultSet.next()) {
                // User found, display user information
            	String name = resultSet.getString("name");
            	String account_no = resultSet.getString("account_no");
            	String email = resultSet.getString("email");
            	String account_type = resultSet.getString("acc_type");
            	String date_of_birth = resultSet.getString("date_of_birth");
            	String id_proof = resultSet.getString("id_proof");
            	String address = resultSet.getString("address");
            	String mobile_no = resultSet.getString("mobile_no");
            	String balance = resultSet.getString("balance");
            	request.setAttribute("name", name);
            	request.setAttribute("account_no", account_no);
            	request.setAttribute("email", email);
                request.setAttribute("account_type", account_type);
                request.setAttribute("date_of_birth", date_of_birth);
                request.setAttribute("id_proof", id_proof);
                request.setAttribute("address", address);
                request.setAttribute("mobile_no", mobile_no);
                request.getRequestDispatcher("viewuser.jsp").forward(request, response);
                
            	
            	
            	
            	
                out.println("<h2>User Information</h2>");
                out.println("<p>Account Number: " + resultSet.getString("account_no") + "</p>");
                out.println("<p>Name: " + resultSet.getString("name") + "</p>");
                out.println("<p>Email: " + resultSet.getString("email") + "</p>");
                // Add more fields as needed
            } else {
                // User not found
                out.println("<h2>User not found</h2>");
            }
        } catch (SQLException e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
    }
}
