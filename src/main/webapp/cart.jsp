<%@page import="java.util.ArrayList"%>
<%@page import="Models.Product"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Cart" %>
<%@ page import="java.text.NumberFormat" %>
<%
    ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productList");
%>
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
                <!-- Bên trái: Tên shop -->
                <a class="navbar-brand text-white fw-bold" href="#">Groud 4 Shop Fashion</a>

                <!-- Giữa: Thanh tìm kiếm -->
                <form class="d-flex mx-auto" action="searchServlet" method="GET">
                    <input class="form-control me-2" type="search" placeholder="Tìm kiếm sản phẩm..." name="query">
                    <button class="btn btn-outline-light" type="submit">Tìm kiếm</button>
                </form>

                <!-- Bên phải: Profile, Đăng Xuất, Giỏ Hàng -->
                <div class="d-flex">
                    <a href="viewProfileServlet" class="btn btn-light me-2">Profile</a>
                    <a href="LogoutServlet" class="btn btn-danger me-2">Đăng Xuất</a>

                    <!-- Giỏ hàng -->
                    <%
                        int totalItems = (request.getAttribute("totalItems") != null) ? (int) request.getAttribute("totalItems") : 0;
                    %>
                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#cartModal">
                        Giỏ hàng (<%= totalItems%>)
                    </button>  
                </div>
            </div>
        </nav>
        <h2>Danh sách sản phẩm</h2>
        <% if (products == null || products.isEmpty()) { %>
        <p>Không có sản phẩm nào.</p>
        <% } else { %>
        <div class="product-container">
            <% for (Product p : products) { %>
            <div class="product-card"  onclick="window.location.href='productDetail.jsp?pid=<%= p.getPID()%>'">
                <% if (p.getImage() != null && !p.getImage().isEmpty()) {%>
                <img src="<%= p.getImage()%>" alt="<%= p.getPName()%>">
                <% } else { %>
                <span>Không có ảnh</span>
                <% }%>
                <h3><%= p.getPName()%></h3>
                <p><%= p.getDetails()%></p>
                <p class="price"><%= String.format("%,.0f", p.getPrice())%> VNĐ</p>
                <form action="cart" method="post" class="order-form">
                    <input type="hidden" name="pid" value="<%= p.getPID()%>">                                      
                    <input type="number" name="quantity" value="1" min="1">
                    <button type="submit" class="cart-btn">Thêm vào giỏ hàng</button>
                </form>
            </div>
            <% } %>
        </div>

        <% }%>
        <!-- Modal giỏ hàng -->
        <div class="modal fade" id="cartModal" tabindex="-1" aria-labelledby="cartModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="cartModalLabel">Giỏ hàng của bạn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>xóa khỏi giỏ hàng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Cart> cartItems = (List<Cart>) request.getAttribute("cartItems");
                                    double totalPrice = 0;
                                    if (cartItems == null || cartItems.isEmpty()) {
                                %>
                                <tr>
                                    <td colspan="4" class="text-center">Giỏ hàng trống</td>
                                </tr>
                                <%
                                } else {
                                    NumberFormat currencyFormat = NumberFormat.getInstance();
                                    for (Cart item : cartItems) {
                                        totalPrice += item.getPrice() * item.getQuantity();
                                %>
                                <tr>
                                    <td><%= item.getPname()%></td>
                                    <td><%= currencyFormat.format(item.getPrice())%> đ</td>
                                    <td>
                                        <form action="cart" method="post" class="cart-quantity">
                                            <input type="hidden" name="action" value="update">
                                            <input type="hidden" name="pid" value="<%= item.getPID()%>">
                                            <input type="number" name="quantity" value="<%= item.getQuantity()%>" min="1" class="form-control">
                                            <button type="submit" class="btn btn-sm btn-success">Cập nhật</button>
                                        </form>
                                    </td>
                                    <td>
                                        <a href="cart?action=delete&pid=<%= item.getCartID()%>" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                                <tr>
                                    <td colspan="2" class="text-end"><strong>Tổng cộng:</strong></td>
                                    <td colspan="2"><strong><%= currencyFormat.format(totalPrice)%> đ</strong></td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <a href="home.jsp" class="btn btn-primary">Thanh toán</a>
                    </div>
                </div>
            </div>
        </div>       
        <script>
            document.getElementById("viewCartBtn").addEventListener("click", function (event) {
                event.preventDefault(); // Ngăn chặn tải lại trang
                var cartModal = new bootstrap.Modal(document.getElementById('cartModal'));
                cartModal.show(); // Hiển thị modal giỏ hàng
            });
        </script>

    </body>
</html>
