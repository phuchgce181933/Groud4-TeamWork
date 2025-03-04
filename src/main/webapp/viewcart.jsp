<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/home.css">
        <title>Danh sách sản phẩm</title>       
        <!-- Thêm icon từ FontAwesome -->
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <h2>Danh sách Sản phẩm</h2>           
        </div>

        <table>
            <tr>
                <th>ID</th>
                <th>Tên sản phẩm</th>
                <th>Mô tả</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Hình ảnh</th>
                <th>Hành động</th>
            </tr>
            <c:forEach items="${data}" var="p">
                <tr>
                    <td>${p.PID}</td>
                    <td>${p.PName}</td>
                    <td>${p.details}</td>
                    <td><strong style="color:#ff6600;">${p.price} VND</strong></td>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden" name="pid" value="${p.PID}">
                            <input type="number" name="quantity" value="1" min="1">
                            <button type="submit">Thêm vào giỏ</button>
                        </form>
                    </td>
                    <td><img src="${pageContext.request.contextPath}/uploads/${p.image}" alt="Product Image"/></td>                  
                </tr>
            </c:forEach>
        </table>

        <c:if test="${not empty errorMessage}">
            <div style="color: red; text-align: center; font-weight: bold;">
                ${errorMessage}
            </div>
        </c:if>

        <script>
            document.querySelectorAll('form').forEach(form => {
                form.addEventListener('submit', function(event) {
                    event.preventDefault();
                    const formData = new FormData(this);
                    fetch('cart', {
                        method: 'POST',
                        body: formData
                    }).then(() => {
                        window.location.href = 'viewcart.jsp';
                    });
                });
            });
        </script>
    </body>
</html>