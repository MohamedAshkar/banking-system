<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Success</title>
    <style>
        body {
         background-image: url(./all2.png);
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
              button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 15px 30px;
            cursor: pointer;
            margin: 10px;
            font-size: 16px;
        }

        button:hover {
            background-color: #0056b3;
        }
        
        h1 {
            color: #27ae60;
        }
        p {
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>User Deleted Successfully</h1>
        <p>The user has been successfully deleted.</p>
        <p>Thank you for using our service.</p>
      <a href="./admindashboard.html" ><button>Home</button></a>
    </div>
</body>
</html>
