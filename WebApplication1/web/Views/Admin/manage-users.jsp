<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.User" %>

<h2>👥 Danh sách người dùng</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Tuổi</th>
        <th>Giới tính</th>
        <th>Vai trò</th>
        <th>Trạng thái</th>
    </tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.age}</td>
            <td><c:out value="${u.gender ? 'Nam' : 'Nữ'}"/></td>
            <td><c:out value="${u.role == 1 ? 'Admin' : 'User'}"/></td>
            <td><c:out value="${u.locked ? '🔒 Khóa' : '✅ Mở'}"/></td>
        </tr>
    </c:forEach>
</table>
