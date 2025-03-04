<%@ page import="java.util.List, Models.Category" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category List</title>
    <style>
        body {
            display: flex;
            background-color: #F8E4A9;
            font-family: Arial, sans-serif;
        }
        .sidebar {
            width: 250px;
            height: 100vh;
            background-color: #ff8c00;
            color: white;
            padding-top: 20px;
            position: fixed;
            top: 0;
            left: 0;
        }
        .sidebar-header {
            text-align: center;
            font-size: 20px;
            font-weight: bold;
        }
        .menu {
            list-style: none;
            padding: 0;
            margin-top: 20px;
        }
        .menu li {
            padding: 10px 20px;
        }
        .menu li a {
            text-decoration: none;
            color: white;
            font-size: 16px;
            display: flex;
            align-items: center;
        }
        .menu li:hover {
            background-color: #e67e22;
            border-radius: 5px;
        }
        .content {
            margin-left: 270px;
            padding: 20px;
            width: calc(100% - 270px);
        }
        .table-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th {
            background-color: #ff8c00;
            color: white;
            padding: 10px;
            text-align: left;
        }
        td {
            padding: 8px;
        }
        .add-btn {
            display: inline-block;
            background-color: #28a745;
            color: white;
            padding: 8px 12px;
            margin-bottom: 10px;
            text-decoration: none;
            border-radius: 5px;
        }
        .add-btn:hover {
            background-color: #218838;
        }
        .action-btn {
            padding: 5px 10px;
            text-decoration: none;
            color: white;
            border-radius: 5px;
            font-size: 14px;
        }
        .edit-btn {
            background-color: #007bff;
        }
        .edit-btn:hover {
            background-color: #0056b3;
        }
        .delete-btn {
            background-color: #dc3545;
            border: none;
            color: white;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 14px;
        }
        .delete-btn:hover {
            background-color: #c82333;
        }
        .delete-form {
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <div class="sidebar-header">ADMIN</div>
        <ul class="menu">
            <li><a href="Admin.jsp"><i class="bx bx-home"></i> Dashboard</a></li>
            <li><a href="UserServlet"><i class="bx bx-user"></i> View Users</a></li>
            <li><a href="CategoryServlet"><i class="bx bx-category"></i> Manage Category</a></li>
        </ul>
    </div>

    <!-- Nội dung danh sách Category -->
    <div class="content">
        <h2>Category List</h2>
        <div class="table-container">
            <a href="add_category.jsp" class="add-btn">+ Add Category</a>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
                <% if (categories != null && !categories.isEmpty()) { 
                    for (Category c : categories) { %>
                    <tr>
                        <td><%= c.getCatID() %></td>
                        <td><%= c.getCatName() %></td>
                        <td><%= c.getDescription() %></td>
                        <td>
                            <a href="edit_category.jsp?CatID=<%= c.getCatID() %>&CatName=<%= c.getCatName() %>&Description=<%= c.getDescription() %>" class="action-btn edit-btn">Edit</a> 
                            
                            <form action="CategoryServlet" method="post" class="delete-form" onsubmit="return confirmDelete()">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="CatID" value="<%= c.getCatID() %>">
                                <input type="submit" value="Delete" class="delete-btn">
                            </form>
                        </td>
                    </tr>
                <% } 
                } else { %>
                    <tr><td colspan="4" style="text-align: center; font-weight: bold;">No categories found.</td></tr>
                <% } %>
            </table>
        </div>
    </div>

    <script>
        function confirmDelete() {
            return confirm("Are you sure you want to delete this category?");
        }
    </script>

</body>
</html>
