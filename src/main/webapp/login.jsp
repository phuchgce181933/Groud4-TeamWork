<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://unpkg.com/bs-brain@2.0.4/components/logins/login-12/assets/css/login-12.css">
    </head>
    <body>
        <!-- Login 12 - Bootstrap Brain Component -->
        <section class="py-3 py-md-5 py-xl-8">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="mb-5">
                            <h2 class="display-5 fw-bold text-center">Đăng nhập</h2>
                            <p class="text-center m-0">Bạn chưa có tài khoản? <a href="register.jsp">Đăng ký</a></p>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-12 col-lg-10 col-xl-8">
                        <div class="row gy-5 justify-content-center">
                            <div class="col-12 col-lg-5">

                                <form action="LoginServlet" method="post">
                                    <div class="row gy-3 overflow-hidden">
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control border-0 border-bottom rounded-0" name="userName" id="userName" placeholder="Tài Khoản" required>
                                                <label for="userName" class="form-label">Tài Khoản</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control border-0 border-bottom rounded-0" name="password" id="password" placeholder="Password" required>
                                                <label for="password" class="form-label">Mật Khẩu</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="row justify-content-between">

                                                <div class="col-6">
                                                    <div class="text-end">
                                                        <a href="forgotpassword.jsp" class="link-secondary text-decoration-none">Quên mật khẩu?</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-grid">
                                                <button class="btn btn-lg btn-dark rounded-0 fs-6" type="submit">Đăng nhập</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <c:if test="${not empty error}">
                                    <div class="text-danger mt-3">
                                        ${error}
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-12 col-lg-2 d-flex align-items-center justify-content-center gap-3 flex-lg-column">
                                <div class="bg-dark h-100 d-none d-lg-block" style="width: 1px; --bs-bg-opacity: .1;"></div>
                                <div class="bg-dark w-100 d-lg-none" style="height: 1px; --bs-bg-opacity: .1;"></div>
                                <div>or</div>
                                <div class="bg-dark h-100 d-none d-lg-block" style="width: 1px; --bs-bg-opacity: .1;"></div>
                                <div class="bg-dark w-100 d-lg-none" style="height: 1px; --bs-bg-opacity: .1;"></div>
                            </div>
                            <div class="col-12 col-lg-5 d-flex align-items-center">
                                <div class="d-flex gap-3 flex-column w-100">
                                    <a href="https://accounts.google.com/o/oauth2/auth?
                                       scope=email%20profile%20openid
                                       &redirect_uri=http://localhost:8080/SWP/login
                                       &response_type=code
                                       &client_id=7335704479-f4pnocts4in3nj8uj0ske03uivbmrr56.apps.googleusercontent.com
                                       &prompt=consent" 
                                       class="btn bsb-btn-2xl btn-outline-dark rounded-0 d-flex align-items-center">

                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-google text-danger" viewBox="0 0 16 16">
                                        <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z" />
                                        </svg>
                                        <span class="ms-2 fs-6 flex-grow-1">Tiếp tục với Google</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
