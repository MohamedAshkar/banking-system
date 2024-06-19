package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deposit")
public class deposit extends HttpServlet {
    private static final String SELECT_BALANCE_QUERY = "SELECT * FROM customer WHERE account_no=?";
    private static final String UPDATE_BALANCE_QUERY = "UPDATE customer SET balance=? WHERE account_no=?";
    private static final String INSERT_TRANSACTION_QUERY = "INSERT INTO transaction (account_no, typet, date_of_tn, amount) VALUES (?, ?, ?, ?)";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get form parameters
        String accountNumber = request.getParameter("account_no");
        double amount = Double.parseDouble(request.getParameter("deposit-amount"));

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "changeme")) {
            // Get current balance
            PreparedStatement selectStatement = con.prepareStatement(SELECT_BALANCE_QUERY);
            selectStatement.setString(1, accountNumber);
            ResultSet resultSet = selectStatement.executeQuery();
            
            double currentBalance = 0;
            if (resultSet.next()) {
                currentBalance = resultSet.getDouble("balance");
            }
          
            // Update balance
            double newBalance = currentBalance + amount;
            PreparedStatement updateStatement = con.prepareStatement(UPDATE_BALANCE_QUERY);
            updateStatement.setDouble(1, newBalance);
            updateStatement.setString(2, accountNumber);
            int rowsUpdated = updateStatement.executeUpdate();
            
            if (rowsUpdated > 0) {
                // Transaction successful
                String transactionType = "deposit";
                logTransaction(con, accountNumber, transactionType, amount);

                // Forward the request to customerdashboard.jsp with updated balance and account details
                String accountType = resultSet.getString("acc_type");
                String name = resultSet.getString("name");
                request.setAttribute("balance", newBalance);
                request.setAttribute("account_type", accountType);
                request.setAttribute("account_no", accountNumber);
                request.setAttribute("name", name);
                request.getRequestDispatcher("customerdashbord.jsp").forward(request, response);
            } else {
                out.println("<h1>Failed to deposit!</h1>");
            }
        } catch (SQLException e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }
    }

    private void logTransaction(Connection con, String accountNumber, String transactionType, double amount) throws SQLException {
        // Log transaction details in the database
        PreparedStatement insertStatement = con.prepareStatement(INSERT_TRANSACTION_QUERY);
        insertStatement.setString(1, accountNumber);
        insertStatement.setString(2, transactionType);
        insertStatement.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
        insertStatement.setDouble(4, amount);
        insertStatement.executeUpdate();
    }
}
