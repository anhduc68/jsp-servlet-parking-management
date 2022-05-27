
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parking Management</title>
    </head>
    <body>
        <h1>Parking Management</h1>
        <form action="checkLoginServlet" method ="Post">
            <label>Username: </label>
            <input type="text" name="username" value="${user.username}" required=""><br/>
            <label>Password: </label>
            <input type="password" name="password" value="${user.password}" required=""><br/>
            <label style="font: italic"> ${msg} </label><br/>
            <input type="submit" value="Đăng Nhập">
        </form>
    </body>
</html>

