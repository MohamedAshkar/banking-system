<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Customer Modification</title>
  <link rel="stylesheet" href="styles.css">
  <style>
    /* Your CSS styles here */
    body {
      font-family: 'Times New Roman', Times, serif;
      margin: 0;
      background-image: url(./all2.png);
      
      
      background-size: cover;
      background-position: center;
      padding: 20px;
    }

    .container {
      display: flex;
      flex-direction: column;
      width: 60%;
      margin-left: 20%;
      margin-top: 50px;
      gap: 10px;
      border: 1px solid #ddd;
      padding: 10px;
      border-radius: 5px;
    }

    h2 {
      text-align: center;
      font-size: 30px;
      color: white;
    }

    .container form {
      display: flex;
      flex-direction: column;
      width: 100%;
      
      gap: 5px;
    }

    label {
      font-weight: bold;
      color: white;
    }

    input[type="text"],
    input[type="email"] {
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 3px;
    }

    button[type="submit"] {
      background-color: #4CAF50; /* Green */
      border: none;
      color: white;
      padding: 10px 20px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      cursor: pointer;
      border-radius: 5px;
    }

    button[type="submit"]:hover {
      background-color: #45A049;
     
    }
    #bt{background-color: #4CAF50; /* Green */
      border: none;
      color: white;
      padding: 10px 20px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      cursor: pointer;
      border-radius: 5px;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Modify Customer Details</h2>
    <input type="text" id="accountNumber" placeholder="Account Number" name="account_no" required>
    <form action="modifyemail" method="post" id="emailForm">
      <input type="hidden" id="emailAccountNumber" name="account_no">
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" placeholder="example@gmail.com">
      <button type="submit" onclick="return validateForm('emailForm')">Modify</button><br>
    </form>
    <form action="modify_address" method="post" id="addressForm">
      <input type="hidden" id="addressAccountNumber" name="account_no">
      <label for="address">Address:</label>
      <input type="text" id="address" name="address" placeholder="Address....">
      <button type="submit" onclick="return validateForm('addressForm')">Modify</button><br>
    </form>
    <form action="modify_mobile" method="post" id="mobileForm">  
      <input type="hidden" id="mobileAccountNumber" name="account_no">
      <label for="mobile_no">Mobile No:</label>
      <input type="text" id="mobile_no" name="mobile_no" placeholder="ex:9340789456">
      <button type="submit" onclick="return validateForm('mobileForm')">Modify</button><br>
    </form>
    <a href="./admindashboard.html" ><button id="bt">Back</button></a>
  </div>

  <script>
    // Function to validate form and set account number
    function validateForm(formId) {
      // Get the account number input field value
      var accountNumber = document.getElementById('accountNumber').value;
      // Set the account number value to the hidden input field in the specified form
      document.getElementById(formId).querySelector('input[name="account_no"]').value = accountNumber;
      // Return true to submit the form
      return true;
    }
  </script>
</body>
</html>