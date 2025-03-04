<%-- 
    Document   : editProfile
    Created on : Feb 17, 2025, 12:17:55 AM
    Author     : Duy Khanh
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Edit Profile</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
            }
            .header {
                background-color: #fd7e14;
                padding: 20px;
                text-align: center;
                color: white;
                font-size: 24px;
            }
            .form-container {
                background-color: white;
                margin: 50px auto;
                padding: 30px;
                width: 50%;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                border-radius: 8px;
            }
            .form-container h1 {
                text-align: center;
                margin-bottom: 30px;
                color: #fd7e14;
            }
            form {
                display: flex;
                flex-direction: column;
            }
            label {
                margin-bottom: 8px;
                font-weight: bold;
            }
            input, select {
                margin-bottom: 20px;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            input[type="submit"] {
                background-color: #fd7e14;
                color: white;
                border: none;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s ease;
            }
            input[type="submit"]:hover {
                background-color: #e76f00;
            }
            .text-danger{
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="header">Edit Profile</div>
        <div class="form-container">
            <h1>Edit Profile</h1>
            <form action="editProfileServlet" method="POST">
                <input type="hidden" name="userID" value="${user.userID}" />

                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" value="${user.fullName}" required />

                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" value="${user.phone}" />

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.email}" readonly/>

                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${user.address}" />

                <label for="gender">Gender:</label>
                <select id="gender" name="gender">
                    <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
                    <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
                    <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Other</option>
                </select>

                <label for="birthday">Birthday:</label>
                <input type="date" id="birthday" name="birthday" value="${user.birthday}" />

                <input type="submit" value="Update Profile" />
                <c:if test="${not empty message}">
                    <div class="text-danger mt-3 " >
                        ${message}
                    </div>
                </c:if>
            </form>
        </div>
    </body>
</html>
