package com.servlet.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/transaction")
public class TransactionHistoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNumber = request.getParameter("account_no");
        Connection connection = null;
        System.out.println("fir1");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///bank", "root", "changeme");
            System.out.println("fir");
            String sql = "SELECT * FROM transaction WHERE account_no = ? ORDER BY date_of_tn DESC LIMIT 10";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("sec");

            List<Transaction> transactions = new ArrayList<>();
            System.out.println("thi");
            while (resultSet.next()) {
                String transactionId = resultSet.getString("typet");
                String date = resultSet.getString("date_of_tn");
                double amount = resultSet.getDouble("amount");
                System.out.println("amount");
                System.out.println(amount);

                Transaction transaction = new Transaction(transactionId, date, amount);
                transactions.add(transaction);
            }
            System.out.println("out");
            // Set transactions as attribute in request
            request.setAttribute("transactions", transactions);
            request.setAttribute("account_no", accountNumber);
            
            // Forward the request to the showacc.jsp page

            // Forward the request to the JSP page
            request.getRequestDispatcher("transactionhistory.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error fetching transaction history: " + e.getMessage());
        } finally {
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
