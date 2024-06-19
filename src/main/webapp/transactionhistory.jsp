<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.servlet.register.Transaction" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction History</title>
    <style>
        button {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
}
h1{
    padding-left: 40%;
}
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        .download-link {
            position: absolute;
            top: 10px;
            right: 10px;
        }
        button:hover{
            background-color: red;
        }
        body {
    font-family: 'Times New Roman', Times, serif;
    background-image: url(./all2.png);
      
      
      background-size: cover;
      background-position: center;
}
    </style>
</head>
<body>
    <a href="#" class="download-link" onclick="downloadTableData()"><button>Download</button></a>
    <h1>Transaction History</h1>
    <table id="transaction-table">
        <tr>
            <th>Transaction Type</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>

        <% 
            List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
            for (Transaction transaction : transactions) {
        %>
            <tr>
                <td><%= transaction.getTransactionType() %></td>
                <td><%= transaction.getAmount() %></td>
                <td><%= transaction.getDate() %></td>
            </tr>
        <% } %>
    </table>
    <center>
    <form action="back" method="post" id="emailForm">
      <input type="hidden"  name="account_no" value="<%= request.getAttribute("account_no") %>">
      <button type="submit" >Back</button><br>
    </form>
</center>
    <script>
        function downloadTableData() {
            const table = document.getElementById("transaction-table");
            const rows = table.getElementsByTagName("tr");
            let pdfcontent = "data:text/pdf;charset=utf-8,";

            for (let i = 0; i < rows.length; i++) {
                const cells = rows[i].getElementsByTagName("td");
                let row = [];
                for (let j = 0; j < cells.length; j++) {
                    row.push(cells[j].innerText);
                }
                pdfcontent += row.join(",") + "\n";
            }

            const encodedUri = encodeURI(pdfcontent);
            const link = document.createElement("a");
            link.setAttribute("href", encodedUri);
            link.setAttribute("download", "transaction_data.pdf");
            document.body.appendChild(link);
            link.click();
        }
    </script>
</body>
</html>