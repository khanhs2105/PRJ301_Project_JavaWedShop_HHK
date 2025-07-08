<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
</head>
<body>
    <h2>Đăng nhập</h2>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="login" method="post">
        Tên đăng nhập: <input type="text" name="name" required><br/>
        Mật khẩu: <input type="password" name="pass" required><br/>
        <button type="submit">Đăng nhập</button>
    </form>
        <p>Bạn chưa có tài khoản? <a href="register">Đăng ký ngay</a></p>
</body>
</html>
