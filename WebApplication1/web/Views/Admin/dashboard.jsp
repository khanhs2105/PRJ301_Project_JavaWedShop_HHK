<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Models.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != 1) {
        response.sendRedirect("../login.jsp"); // Không phải admin thì đá ra
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
</head>
<body>
    <h1> Xin chào Admin: <%= user.getName() %></h1>

    <ul>
        <li><a href="${pageContext.request.contextPath}/admin/manage-users">Quản lý người dùng</a></li>
        <li><a href="../manage-products.jsp">Quản lý sản phẩm</a></li>
        <li><a href="../view-orders.jsp">Xem đơn hàng</a></li>
        <li><a href="../logout.jsp">Đăng xuất</a></li>
    </ul>
</body>
</html>
