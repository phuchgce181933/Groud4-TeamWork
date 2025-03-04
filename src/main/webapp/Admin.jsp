<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Dashboard</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

    <style>
        body {
            display: flex;
            background-color: #F8E4A9;
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
        .main-content {
            margin-left: 260px;
            padding: 20px;
            width: 100%;
        }
        .content-header {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .table-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .table th {
            background-color: #ff8c00;
            color: white;
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

    <div class="main-content">
        <div class="content-header">User List</div>
        <div class="table-container">
            <table class="table table-bordered text-center">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Gender</th>
                        <th>Birthday</th>
                        <th>Status</th>
                        <th>Account ID</th>
                    </tr>
                </thead>
                <tbody>
                    <% List<User> users = (List<User>) request.getAttribute("users"); %>
                    <% if (users != null && !users.isEmpty()) { %>
                        <% for (User user : users) { %>
                        <tr>
                            <td><%= user.getUserID() %></td>
                            <td><%= user.getFullName() %></td>
                            <td><%= user.getPhone() %></td>
                            <td><%= user.getEmail() %></td>
                            <td><%= user.getAddress() %></td>
                            <td><%= user.getGender() %></td>
                            <td><%= user.getBirthday() %></td>
                            <td><%= user.isUserStatus() ? "Active" : "Inactive" %></td>
                            <td><%= user.getAccountID() %></td>
                        </tr>
                        <% } %>
                    <% } else { %>
                        <tr><td colspan="9">No users found.</td></tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
