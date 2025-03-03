<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Models.Product" %>
<%
    Product product = (Product) request.getAttribute("product");
    if (product == null) {
        response.sendRedirect("product"); // Nếu product bị null, quay lại danh sách
        return;
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa sản phẩm</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h2>Chỉnh sửa sản phẩm</h2>
    <form action="product?action=update" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="pid" value="<%= product.getPID() %>">
        
        <label>Tên sản phẩm:</label>
        <input type="text" name="pname" value="<%= product.getPName() %>" required><br>

        <label>Mô tả:</label>
        <textarea name="details" required><%= product.getDetails() %></textarea><br>

        <label>Giá:</label>
        <input type="number" name="price" value="<%= product.getPrice() %>" min="1" required><br>

        <label>Số lượng:</label>
        <input type="number" name="quantity" value="<%= product.getQuantity() %>" min="1" required><br>

        <label>Danh mục (CatID):</label>
        <input type="number" name="catID" value="<%= product.getCatID() %>" min="1" required><br>

        <label>Hình ảnh (URL):</label>
        <input type="text" name="image" value="<%= product.getImage() %>"><br>

        <button type="submit">Cập nhật</button>
    </form>

    <script>
        function validateForm() {
            let price = document.getElementsByName("price")[0].value;
            let quantity = document.getElementsByName("quantity")[0].value;
            if (price <= 0 || quantity <= 0) {
                alert("⚠️ Giá và số lượng phải lớn hơn 0!");
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
