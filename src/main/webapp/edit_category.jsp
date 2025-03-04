<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String catID = request.getParameter("CatID");
    String catName = request.getParameter("CatName");
    String description = request.getParameter("Description");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Category</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 400px;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            font-weight: bold;
            margin-top: 10px;
            text-align: left;
        }
        input[type="text"] {
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 100%;
        }
        input[type="submit"] {
            margin-top: 15px;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .back-btn {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 15px;
            background-color: #6c757d;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .back-btn:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Edit Category</h2>
        <form action="CategoryServlet" method="post">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="CatID" value="<%= catID %>">
            
            <label>Name:</label>
            <input type="text" name="CatName" value="<%= catName %>" required>

            <label>Description:</label>
            <input type="text" name="Description" value="<%= description %>">

            <input type="submit" value="Update">
        </form>
        <a href="CategoryServlet" class="back-btn">Back</a>
    </div>

</body>
</html>
