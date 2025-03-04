<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Sản Phẩm</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                text-align: center;
            }
            .category-select {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: white;
            }
            .container {
                max-width: 600px;
                margin: 40px auto;
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
            }

            h2 {
                color: #ff6600;
            }

            form {
                display: flex;
                flex-direction: column;
            }

            label {
                text-align: left;
                margin: 10px 0 5px;
                font-weight: bold;
            }

            input, textarea {
                width: 100%;
                padding: 8px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            button {
                background-color: #ff6600;
                color: white;
                border: none;
                padding: 10px;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
            }

            button:hover {
                opacity: 0.8;
            }

            .back {
                display: inline-block;
                margin-top: 10px;
                color: #ff6600;
                text-decoration: none;
            }

            .back:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Thêm Sản Phẩm Mới</h2>
            <form action="addproduct" method="post"enctype="multipart/form-data">
                <label for="name">Tên sản phẩm:</label>
                <input type="text" name="name" required>
                <label for="category">Danh mục:</label>
                <select name="category" class="category-select" required>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.catID}">${category.catName}</option>
                    </c:forEach>
                </select>

                <label for="details">Mô tả:</label>
                <textarea name="details" required></textarea>

                <label for="price">Giá (VND):</label>
                <input type="number" name="price" required>

                <label for="quantity">Số lượng:</label>
                <input type="number" name="quantity" required>

                <label for="image">Hình ảnh:</label>
                <input type="file" name="image" accept="image/*" required>

                <button type="submit">Thêm sản phẩm</button>
            </form>
            <a href="ViewProduct" class="back">← Quay lại danh sách</a>
        </div>

        <c:if test="${not empty errorMessage}">
            <div>
                ${errorMessage}

            </div>

        </c:if>
    </body>
</html>
