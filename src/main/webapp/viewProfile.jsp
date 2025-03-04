<%-- 
    Document   : profile
    Created on : Feb 16, 2025, 10:32:18 PM
    Author     : Duy Khanh
--%>

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
    .profile-container {
        background-color: white;
        margin: 50px auto;
        padding: 30px;
        width: 50%;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        border-radius: 8px;
    }
    .profile-container h1 {
        text-align: center;
        margin-bottom: 30px;
        color: #fd7e14;
    }
    .profile-item {
        margin: 15px 0;
        font-size: 18px;
    }
    .profile-item span {
        font-weight: bold;
    }
    .button-container {
        display: flex;
        justify-content: center;
        gap: 15px;
        margin-top: 30px;
    }
    .button-container a, .back-button a {
        text-decoration: none;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 16px;
        transition: background-color 0.3s ease;
    }
    .button-container a {
        background-color: #fd7e14;
        color: white;
    }
    .button-container a:hover {
        background-color: #e76f00;
    }
    .back-button {
        text-align: center;
        margin-top: 20px;
    }
    .back-button a {
        background-color: #007bff;
        color: white;
    }
    .back-button a:hover {
        background-color: #0056b3;
    }
</style>

<div class="header">User Profile</div>
<div class="profile-container">
    <h1>View Profile</h1>
    <div class="profile-item"><span>UserID:</span> ${user.userID}</div>
    <div class="profile-item"><span>Full Name:</span> ${user.fullName}</div>
    <div class="profile-item"><span>Phone:</span> ${user.phone}</div>
    <div class="profile-item"><span>Email:</span> ${user.email}</div>
    <div class="profile-item"><span>Address:</span> ${user.address}</div>
    <div class="profile-item"><span>Gender:</span> ${user.gender}</div>
    <div class="profile-item"><span>Birthday:</span> ${user.birthday}</div>

    <div class="button-container">
        <a href="editProfileServlet">Edit Profile</a>
        <a href="ChangepasswordServlet">Change password</a>
    </div>
    
    <div class="back-button">
        <a href="home.jsp">Back</a>
    </div>
</div>
