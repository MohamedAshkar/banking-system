<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View User</title>
    <style>
        body {
            font-family:'Times New Roman', Times, serif;
            background-image: url(./all2.png);
      background-size: cover;
      background-position: center;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .input-group {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 20px;
        }

        input {
            padding: 10px;
            font-size: 16px;
            margin-right: 10px;
            border: 1px solid #666363;
            border-radius: 5px;
            width: 200px;
        }

        button {
            background-color:#4CAF50;
            color: #fff;
            border: none;
            padding: 15px 30px;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <form  action="view" method="post" id="login-form" >
            <h2>View User</h2>
            <div class="input-group">
                <input type="text" id="accountNumber" placeholder="Account Number" name="account_no">
                <button type="submit">View</button>
            
        </form>
      
         </div>
<a href="./admindashboard.html" ><button>Home</button></a>
        <!-- Add content to display user information here -->
        <%
            // Check if request attributes are not null before displaying them
            if (request.getAttribute("name") != null) {
        %>
            <h4>Name: <%= request.getAttribute("name") %></h4>
            <h4>Account Number: <%= request.getAttribute("account_no") %></h4>
            <h4>Email: <%= request.getAttribute("email") %></h4>
            <h4>Mobile Number: <%= request.getAttribute("mobile_no") %></h4>
            <h4>Date Of Birth: <%= request.getAttribute("date_of_birth") %></h4>
            <h4>Account Type: <%= request.getAttribute("account_type") %></h4>
            <h4>Address: <%= request.getAttribute("address") %></h4>
            <h4>ID Proof: <%= request.getAttribute("id_proof") %></h4>
        <% } %>
    </div>

</body>
</html>