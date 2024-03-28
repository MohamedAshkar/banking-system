<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        h1 {
            color: #e74c3c;
        }
        p {
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Error Encountered</h1>
        <p>An error occurred while processing your request:</p>
        <p><%= request.getParameter("msg") %></p>
        <p>Please try again later or contact support for assistance.</p>
    </div>
</body>
</html>
