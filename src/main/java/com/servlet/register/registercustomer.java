package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class registercustomer extends HttpServlet{

    //create the query
    private static final String INSERT_QUERY ="INSERT INTO customer(NAME,address,account_no,password,mobile_no,email,acc_type,date_of_birth,id_proof,balance) VALUES(?,?,?,?,?,?,?,?,?,?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter pw = res.getWriter();
        //set Content type
        res.setContentType("text/hmtl");
        String constantAlphabets = "ABCD";
        Random random = new Random();
        StringBuilder randomDigits = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            randomDigits.append(random.nextInt(10)); // Generates a random digit (0-9)
        }
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char randomChar = (char) ('A' + random.nextInt(26)); // Generates a random uppercase alphabet
            password.append(randomChar);
        }

        // Concatenate the constant alphabets with 6 random digits

        //read the form values
        String name = req.getParameter("full_name");
        String address = req.getParameter("address");
        String mobile_no = req.getParameter("mobile_no");
        String email = req.getParameter("email");
        String account_type = req.getParameter("account_type");
        String initial_balance = req.getParameter("initial_balance");
        String date_of_birth = req.getParameter("date_of_birth");
        String id_proof = req.getParameter("id_proof");
        String account_no = constantAlphabets + randomDigits.toString();
        String userpassword = password.toString();

        //load the jdbc driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //create the connection
        try(Connection con = DriverManager.getConnection("jdbc:mysql:///bank","root","changeme");
                PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
            //set the values
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, account_no);
            ps.setString(4, userpassword);
            ps.setString(5, mobile_no);
            ps.setString(6, email);
            ps.setString(7, account_type);
            ps.setString(8, date_of_birth);
            ps.setString(9, id_proof);
            ps.setString(10, initial_balance);


            //execute the query
            int count = ps.executeUpdate();

            if(count==0) {
            	//res.sendRedirect("showacc.html");
            }else {
            	req.setAttribute("accountNo", account_no);
                req.setAttribute("password", password);
                
                // Forward the request to the showacc.jsp page
                req.getRequestDispatcher("showacc.jsp").forward(req, res);
            }
        }catch(SQLException se) {
            pw.println(se.getMessage());
            se.printStackTrace();
        }catch(Exception e) {
            pw.println(e.getMessage());
            e.printStackTrace();
        }

        //close the stream
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }
}
