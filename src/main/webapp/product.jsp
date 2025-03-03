<%@page import="Models.Product"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Product" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Danh sách sản phẩm</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="text-center">Danh Sách Sản Phẩm</h2>

            <!-- Nút Thêm Sản Phẩm -->
            <div class="d-flex justify-content-end mb-3">
                <button class="btn btn-success" onclick="showAddForm()">Thêm Sản Phẩm</button>
            </div>

            <!-- Bảng Hiển Thị Sản Phẩm -->
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Tên sản phẩm</th>
                        <th>Chi tiết</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Danh mục</th>
                        <th>Hình ảnh</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Product> productList = (List<Product>) request.getAttribute("productList");
                        if (productList != null && !productList.isEmpty()) {
                            for (Product p : productList) {
                    %>
                    <tr>
                        <td><%= p.getPID()%></td>
                        <td><%= p.getPName()%></td>
                        <td><%= p.getDetails()%></td>
                        <td><%= p.getPrice()%> VND</td>
                        <td><%= p.getQuantity()%></td>
                        <td><%= p.getCatID()%></td>
                        <td>
                            <% if (p.getImage() != null) {%>
                            <img src="<%= p.getImage()%>" alt="Hình sản phẩm" width="50">
                            <% }%>
                        </td>
                        <td>
                            <a href="product?action=delete&pid=<%= p.getPID()%>" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">Xóa</a>
                            <button class="btn btn-warning btn-sm" onclick="showEditForm(<%= p.getPID()%>, '<%= p.getPName()%>', '<%= p.getDetails()%>', <%= p.getPrice()%>, <%= p.getQuantity()%>, <%= p.getCatID()%>, '<%= p.getImage()%>')">Sửa</button>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="8" class="text-center">Không có sản phẩm nào</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>

            <!-- Form Thêm/Sửa Sản Phẩm (Ẩn Mặc Định) -->
            <div id="productForm" class="card p-4" style="display: none;">
                <h3 id="formTitle">Thêm Sản Phẩm</h3>
                <form action="product" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="pid" id="pid">
                    <input type="hidden" name="action" id="action" value="add">

                    <div class="mb-3">
                        <label for="pname" class="form-label">Tên sản phẩm:</label>
                        <input type="text" class="form-control" name="pname" id="pname" required>
                    </div>

                    <div class="mb-3">
                        <label for="details" class="form-label">Chi tiết:</label>
                        <textarea class="form-control" name="details" id="details" required></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="price" class="form-label">Giá:</label>
                        <input type="number" class="form-control" name="price" id="price" required>
                    </div>

                    <div class="mb-3">
                        <label for="quantity" class="form-label">Số lượng:</label>
                        <input type="number" class="form-control" name="quantity" id="quantity" required>
                    </div>

                    <div class="mb-3">
                        <label for="catID" class="form-label">Danh mục:</label>
                        <input type="number" class="form-control" name="catID" id="catID" required>
                    </div>

                    <div class="mb-3">
                        <label for="image" class="form-label">Hình ảnh:</label>
                        <input type="file" class="form-control" name="image" id="image">
                        <img id="previewImage" src="" width="100" style="display: none; margin-top: 10px;">
                    </div>

                    <button type="submit" class="btn btn-primary">Lưu</button>
                    <button type="button" class="btn btn-secondary" onclick="hideForm()">Hủy</button>
                </form>
            </div>
        </div>

        <script>
            function showAddForm() {
                document.getElementById("formTitle").innerText = "Thêm Sản Phẩm";
                document.getElementById("action").value = "add";
                document.getElementById("pid").value = "";
                document.getElementById("pname").value = "";
                document.getElementById("details").value = "";
                document.getElementById("price").value = "";
                document.getElementById("quantity").value = "";
                document.getElementById("catID").value = "";
                document.getElementById("image").value = "";
                document.getElementById("previewImage").style.display = "none";
                document.getElementById("productForm").style.display = "block";
            }

            function showEditForm(pid, pname, details, price, quantity, catID, image) {
                document.getElementById("formTitle").innerText = "Chỉnh Sửa Sản Phẩm";
                document.getElementById("action").value = "edit";
                document.getElementById("pid").value = pid;
                document.getElementById("pname").value = pname;
                document.getElementById("details").value = details;
                document.getElementById("price").value = price;
                document.getElementById("quantity").value = quantity;
                document.getElementById("catID").value = catID;

                if (image) {
                    document.getElementById("previewImage").src = image;
                    document.getElementById("previewImage").style.display = "block";
                } else {
                    document.getElementById("previewImage").style.display = "none";
                }

                document.getElementById("productForm").style.display = "block";
            }

            function hideForm() {
                document.getElementById("productForm").style.display = "none";
            }
        </script>

    </body>
</html>
