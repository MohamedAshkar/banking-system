<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>user rigistration</title>
    <style>
        body{
            font-family: 'Times New Roman', Times, serif;
            background-image: url(./all.png);
            background-size: cover;
            background-position: center;
        }
        button{
            display: flex;
            margin-left: 80%;
             margin-top:30px ;
             background-color: blue;
             text-decoration: none;
             color: white;
             padding: 10px;
             border: none;
             border-radius: 5px;
             
  
            


        }
        div{
            margin-top: 20px;
            background-color: rgb(236, 235, 234);
            width: 400px;
            padding: 4%;
            padding-left: 0px;
            margin-left: 30%;
        }
        h3{
            padding-left: 30%;
            padding-top: 30px;

        }
    </style>
</head>
<body>

    <a href="./admindashboard.html"><button>HOME</button></a>
           <div>
            

        
    
            <h3>Account Number: <%= request.getAttribute("accountNo") %></h3>
    <h3>Password: <%= request.getAttribute("password") %></h3>
           </div>
        
            
            
            
    
</body>
</html>