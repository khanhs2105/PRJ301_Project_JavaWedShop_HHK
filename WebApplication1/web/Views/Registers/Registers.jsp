<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
</head>
<body>
    <h2>Đăng ký tài khoản</h2>

    <!-- Hiển thị lỗi nếu có -->
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="register" method="post">
        Tên đăng nhập: <input type="text" name="name" required><br/>
        Mật khẩu: <input type="password" name="password" required><br/>
        Tuổi: <input type="number" name="age" min="18" required><br/>
        Giới tính:
        <select name="gender">
            <option value="true">Nam</option>
            <option value="false">Nữ</option>
        </select><br/>
        <button type="submit">Đăng ký</button>
    </form>
</body>
</html>
