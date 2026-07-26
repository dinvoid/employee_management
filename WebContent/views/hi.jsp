<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
    <h1>Hello JSP views</h1>
    
    <form name="employeeForm" method="post">
        Name:
        <input type="text" name="employeeName">
        <input type="button" value="Search" onClick="search()">
    </form>

    <h2>Result:</h2>
    <p>${employeeName}
    
    <script type="text/javascript">
     function search(){
       document.employeeForm.action = "Display.action";
       document.employeeForm.method = "post"; // ensure POST
       document.employeeForm.submit();
     }
    </script>
</body>
</html>
