<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != 0) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang người dùng</title>
</head>
<body>
    <h2>Chào mừng, <c:out value="${user.name}"/>!</h2>

    <p>Đây là trang dành cho người dùng thông thường.</p>

    <ul>
        <li><a href="../profile.jsp">Thông tin cá nhân</a></li>
        <li><a href="../shop.jsp">Xem sản phẩm</a></li>
        <li><a href="../cart.jsp">Giỏ hàng</a></li>
        <li><a href="../logout.jsp">Đăng xuất</a></li>
    </ul>
</body>
</html>
