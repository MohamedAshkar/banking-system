package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteuser")
public class DeleteUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("account_no");
        
        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://localhost:3306/bank";
        String username = "root";
        String password = "changeme";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to MySQL database
            conn = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement to delete user
            String sql = "DELETE FROM customer WHERE account_no = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNumber);

            // Execute the SQL statement
            int rowsAffected = stmt.executeUpdate();

            // Check if user is deleted successfully
            if (rowsAffected > 0) {
                // User deleted successfully
                response.sendRedirect("delete_success.jsp"); // Redirect to success JSP page
            } else {
                // User not found or deletion failed
                response.sendRedirect("error.jsp"); // Redirect to failure JSP page
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Handle exception or redirect to error JSP page with a specific error message
            response.sendRedirect("error.jsp?msg=" + ex.getMessage());
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
