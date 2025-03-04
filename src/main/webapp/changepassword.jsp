<%-- 
    Document   : changepassword
    Created on : Feb 28, 2025, 11:34:51 AM
    Author     : Duy Khanh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    .container {
        background-color: white;
        margin: 50px auto;
        padding: 30px;
        width: 40%;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        border-radius: 8px;
    }
    .container h1 {
        text-align: center;
        color: #fd7e14;
    }
    .form-group {
        margin: 15px 0;
    }
    label {
        font-weight: bold;
    }
    input {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .button-container {
        display: flex;
        justify-content: center;
        gap: 15px;
        margin-top: 20px;
    }
    .button-container button, .back-button a {
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 16px;
        border: none;
        cursor: pointer;
    }
    .submit-btn {
        background-color: #fd7e14;
        color: white;
    }
    .submit-btn:hover {
        background-color: #e76f00;
    }
    .back-button {
        text-align: center;
        margin-top: 20px;
    }
    .back-button a {
        background-color: #007bff;
        color: white;
        text-decoration: none;
    }
    .back-button a:hover {
        background-color: #0056b3;
    }
</style>

<div class="header">Change Password</div>
<div class="container">
    <h1>Change Password</h1>
    <form action="ChangepasswordServlet" method="post">
        <div class="form-group">
            <label>Old Password:</label>
            <input type="password" name="oldPassword" required>
        </div>
        <div class="form-group">
            <label>New Password:</label>
            <input type="password" name="newPassword" required>
        </div>
        <div class="form-group">
            <label>Confirm New Password:</label>
            <input type="password" name="confirmPassword" required>
        </div>
        <div class="button-container">
            <button type="submit" class="submit-btn">Change Password</button>
        </div>

        <c:if test="${not empty error}">
            <div class="text-danger mt-3">
                ${error}
            </div>
        </c:if>

    </form>
    <div class="back-button">
        <a href="viewProfileServlet">Back</a>
    </div>
</div>

