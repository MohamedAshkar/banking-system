<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw failed</title>
    <style>
        body {
        background-image: url(./all2.png);
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 100px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            color: #27ae60;
        }
        p {
            color: #333;
        }
         button {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
}
button:hover{
            background-color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>WITHDRAW FAILED</h2>
        <p>sorry, The amount you have entered is exiting the balance amount</p>
        <form action="back" method="post" id="emailForm">
      <input type="hidden"  name="account_no" value="<%= request.getAttribute("account_no") %>">
      <button type="submit" >Back</button><br>
    </form>
    </div>
</body>
</html>