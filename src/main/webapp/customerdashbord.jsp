<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
   
    <style>
        body {
            font-family: sans-serif;
            font-family: 'Times New Roman', Times, serif;
           background-image: url(./all2.png);
      
      
      background-size: cover;
      background-position: center;
            margin: 0;
            padding: 0;
            background-color: #fff9f9;
        }

        header {
            background-color: rgb(218, 212, 212);
            color: #171616;
            padding: 25px;
            text-align: center;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        h1, h2 {
            margin: 40px 0;
        }

        main {
            display: flex;
            justify-content: space-around;
            padding: 20px;
        }

        section {
            flex: 1 1 30%;
            margin: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #fff;
        }

        section h2 {
            margin-top: 0;
        }

        .actions form {
            margin-top: 10px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="number"] {
            width: 90%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
            margin-bottom: 10px;
        }

        button {
            background-color: #3eb06a;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #f56565;
        }

        .transactions {
            display: flex;
            justify-content: flex-end;
            margin-top: 10px;
            background-color: transparent;
            align-items: flex-end;
        }

        
       
        p{ font-size:40px;
        }
        li{
        padding:10px;
        }
        #deletebtn{
          margin-left: 87.5%;
          margin-top:0px;
        }
        
        
       



    </style>
</head>
<body>
 
    <header>
      
      
        <h1>Customer Dashboard</h1>
        <p>Welcome, <%= request.getAttribute("name") %></p>
        
        
        <form action="transaction" method="post" id="login-form">
            <input type="hidden" id="account-no" name="account_no" value="<%= request.getAttribute("account_no") %>">
            
            <button type="submit" id="transaction"> Transaction History</button>
        </form>
        <a href="./frontlogin.html" ><button id="logout" >Logout</button></a>
    </header>
    
    <main>
        <section class="account-info">
            <h2>Account Details</h2>
            <ul>
                <li><h3>Account Number:</h2><%= request.getAttribute("account_no") %><span id="account-number"></span></li>
                <li><h3>Account Type: </h2><%= request.getAttribute("account_type") %><span id="account-type"></span></li>
                <li><h3>Available Balance:</h2><%= request.getAttribute("balance") %> <span id="account-balance"></span></li>
            </ul>
        </section>
        <section class="actions">
            <h2>Account Actions</h2>
            <form action="withdraw" method="post" id="login-form">
                <input type="hidden" id="account-no" name="account_no" value="<%= request.getAttribute("account_no") %>">
                <label for="withdraw-amount">Withdraw Amount:</label>
                <input type="number" id="withdraw-amount" name="withdraw-amount" min="0" required>
                <button type="submit">Withdraw</button>
            </form>
            <form action="deposit" method="post" id="login-form">
                <input type="hidden" id="account-no" name="account_no" value="<%= request.getAttribute("account_no") %>">
                <label for="deposit-amount">Deposit Amount:</label>
                <input type="number" id="deposit-amount" name="deposit-amount" min="0" required>
                <button type="submit">Deposit</button>
            </form>
        </section>
    </main>
    <form action="deleteuser" method="post" id="delete-form">
          
        <input type="hidden" id="accountNumber" name="account_no" value="<%= request.getAttribute("account_no") %>">
       <button type="button"  id="deletebtn"  onclick="checkBalance()">Delete Account</button>
</form>
    <script>
        function checkBalance() {
            var accountNumber = document.getElementById("accountNumber").value;
            if (!accountNumber.trim()) {
                alert("Please enter an account number.");
                return;
            }
            // AJAX request to servlet to check balance
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "checkbalance", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        var balance = parseInt(xhr.responseText);
                        if (balance === 0) {
                            document.getElementById("delete-form").submit();
                        } else {
                            alert("Balance is not 0. Please withdraw the money before deleting the user.");
                        }
                    } else {
                        alert("Error: " + xhr.statusText);
                    }
                }
            };
            xhr.send("account_no=" + accountNumber);
        }
    </script>
</body>
</html>                