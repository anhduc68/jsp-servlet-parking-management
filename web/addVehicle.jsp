
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parking Management</title>
    </head>
    <body>
        <h1>CarKeeper Home</h1>
        <a href="/">Booking</a>
        <a href="searchBookingServlet">Checkin</a>
        <a href="checkoutBookingServlet">Checkout</a>
        <a href="/">Buy MonthTicket</a>
        <a href="/">Buy VipTicket</a>
        <h1>Thêm Xe</h1>
         <a href="/parkingmanagement/addCustomer.jsp">Thêm Khách Hàng</a>
        <form action="addVehicleServlet" method ="Post">
            <label>Nhập Kiểu Xe: </label>
            <select name="type">
                <option value="motor">Xe máy</option>
                 <option value="car">Ô tô</option>
            </select><br/>
            <label>Nhập Biển Số Xe:  </label>
            <input type="text" name="licencePlate" value="${vehicle.licencePlate}" required=""><br/>
            <label style="font: italic"> ${msg} </label><br/>
            <select name="customer_id">
                <c:forEach var="customer" items="${listCustomer}">
                    <option value="${customer.id}">${customer.fullname}</option>
                </c:forEach>
            </select>
            <input type="submit" name="Thêm">
        </form>
    </body>
</html>
