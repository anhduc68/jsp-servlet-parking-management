
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
        <h1>Tìm Xe</h1>
        <form action="searchVehicleServlet" method ="Post">
            <label>Nhập Biển Số Xe: </label>
            <input type="text" name="licencePlate" value="${licencePlate}">
            <input type="submit" name="Tìm Kiếm">
            <a href="addVehicleServlet">Thêm Xe</a>
        </form>
         <table cellspacing="5" cellpadding="5" border="1">   
            <tr>
                <td>ID</td>
                <td>Loại</td>
                <td>Biển Số Xe</td>
                <td>Chủ Sở Hữu</td>
                <td>Action</td>
            </tr>
            <c:forEach var="vehicle" items="${listVehicle}">
                <tr valign="top">
                    <td>${vehicle.id}</td>
                    <td>${vehicle.type}</td>
                    <td>${vehicle.licencePlate}</td>
                    <td>${vehicle.customer.fullname}</td>
                    <td><a href="searchVacantServlet?vehicle_id=${vehicle.id}">Chọn</a></td>
                </tr>
            </c:forEach>
        </table>    
            
    </body>
</html>
