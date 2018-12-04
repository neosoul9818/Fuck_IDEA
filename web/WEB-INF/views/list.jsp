<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/12
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>员工列表</title>
</head>
<body>
    <c:choose>
        <c:when test="${empty requestScope.employees}">
            <h3 align="center" color="red">公司黄了，员工集体跳槽了</h3>
        </c:when>
        <c:otherwise>
            <h3 align="center">吹牛逼有限责任公司</h3>

            <table align="center" border="70%">
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>部门</th>
                </tr>

                <c:forEach items="${requestScope.employees}" var="emp">

                    <tr>
                        <td>${emp.id}</td>
                        <td>${emp.lastName}</td>
                        <td>${emp.gender}</td>
                        <td>${emp.email}</td>
                        <td>${emp.department.deptName}</td>
                    </tr>
                </c:forEach>

            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
