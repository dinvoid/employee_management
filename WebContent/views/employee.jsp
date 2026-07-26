
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ww" uri="webwork" %>


<html>
<body>
ok
<ww:property value="name"/>

<h1>Create Employee</h1>

<form name="employeeForm" method="post">
    Name:
    <input type="text" name="name">
    Department:
    <input type="text" name="department">
    <input type="button" value="Submit" onclick="createEmployee()">
</form>

<form name="searchForm" method="get">
    <input type="button" value="Search All Employeesw" onclick="searchAllEmployee()">
</form>

<hr>
${name}
<br>
${department}
<h2>Debug</h2>
${pageScope.empList}
<br>
${requestScope.empList}
<br>
${sessionScope.empList}
<br>
${applicationScope.empList}

empList = ${empList}
<br>
empty? = ${empty empList}
<br>

<c:if test="${not empty empList}">
    Size = ${empList.size()}
</c:if>

<hr>

<h2>Employees</h2>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Department</th>
    </tr>

    <c:choose>
        <c:when test="${not empty empList}">
            <c:forEach var="emp" items="${empList}">
                <tr>
                    <td>${emp.name}</td>
                    <td>${emp.department}</td>
                </tr>
            </c:forEach>
        </c:when>

        <c:otherwise>
            <tr>
                <td colspan="2">No employees found.</td>
            </tr>
        </c:otherwise>
    </c:choose>

</table>

<script>
function createEmployee() {
    document.employeeForm.action = "CreateEmployee.action";
    document.employeeForm.submit();
}

function searchAllEmployee() {
    document.searchForm.action = "EmployeeMaint.action";
    document.searchForm.submit();
}
</script>

</body>
</html>