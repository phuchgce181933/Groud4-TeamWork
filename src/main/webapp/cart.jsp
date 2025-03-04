<%@page import="Models.Cart"%>
<%@page import="java.util.List"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="Models.Cart"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>   
    <head>
        <title>Giỏ hàng</title>
        <!-- Bootstrap CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="css/home.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg" style="background-color: #FFA500; padding: 10px;">
            <div class="container-fluid">
                <a class="navbar-brand text-white fw-bold" href="#">Group 4 Shop Fashion</a>

                <form class="d-flex mx-auto" action="searchServlet" method="GET">
                    <input class="form-control me-2" type="search" placeholder="Tìm kiếm sản phẩm..." name="query">
                    <button class="btn btn-outline-light" type="submit">Tìm kiếm</button>
                </form>

                <div class="d-flex">
                    <a href="viewProfileServlet" class="btn btn-light me-2">Profile</a>
                    <a href="LogoutServlet" class="btn btn-danger me-2">Đăng Xuất</a>

                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#cartModal">
                        Giỏ hàng
                    </button>  
                </div>
            </div>
        </nav>

        <h2 class="text-center mt-4">Giỏ hàng của bạn</h2>

        <div class="container mt-3">
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Hình ảnh</th>
                        <th>Sản phẩm</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Tổng</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Cart> cartItems = (List<Cart>) request.getAttribute("cartList");
                        double totalPrice = 0;
                        NumberFormat currencyFormat = NumberFormat.getInstance();

                        if (cartItems == null || cartItems.isEmpty()) {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">Giỏ hàng trống</td>
                    </tr>
                    <% } else {
                        for (Cart item : cartItems) {
                            double itemTotal = item.getPrice() * item.getQuantity();
                            totalPrice += itemTotal;
                    %>
                    <tr>
                        <td>
                            <% if (item.getImage() != null && !item.getImage().isEmpty()) {%>
                            <img src="<%= item.getImage()%>" alt="<%= item.getPname()%>" width="80">
                            <% } else { %>
                            <span>Không có ảnh</span>
                            <% }%>
                        </td>
                        <td><%= item.getPname()%></td>
                        <td><%= currencyFormat.format(item.getPrice())%> VNĐ</td>
                        <td>
                            <form action="cart" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="pid" value="<%= item.getPID()%>">
                                <input type="number" name="quantity" value="<%= item.getQuantity()%>" min="1" class="form-control" style="width: 70px; display: inline;">
                                <button type="submit" class="btn btn-sm btn-success">Cập nhật</button>
                            </form>
                        </td>
                        <td><%= currencyFormat.format(itemTotal)%> VNĐ</td>
                        <td>
                            CartID: <%= item.getCartID()%> <!-- Kiểm tra CartID có đúng không -->
                        </td>
                        <td>
                            <form action="cart" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="cartID" value="<%= item.getCartID()%>">
                                <button type="submit" class="btn btn-danger">🗑 Xóa</button>
                            </form>
                        </td>

                    </tr>
                    <% }%>
                    <tr>
                        <td colspan="4" class="text-end"><strong>Tổng cộng:</strong></td>
                        <td colspan="2"><strong><%= currencyFormat.format(totalPrice)%> VNĐ</strong></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <div class="container text-center mt-3">
            <a href="home.jsp" class="btn btn-primary">Tiếp tục mua sắm</a>
            <% if (cartItems != null && !cartItems.isEmpty()) { %>
            <a href="checkout.jsp" class="btn btn-success">Thanh toán</a>
            <% }%>
        </div>

    </body>
</html>
