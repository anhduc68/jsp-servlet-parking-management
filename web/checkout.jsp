
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
        <h1>Checkout</h1>
        <form action="searchBookingServlet" method ="Post">
            <label>Nhập Biển Số Xe: </label>
            <input type="text" name="licencePlate" value="${licencePlate}" required="">
            <input type="submit" value="Tìm Kiếm">
        </form>

        <table cellspacing="5" cellpadding="5" border="1">   
            <tr>
                <td>ID</td>
                <td>Chủ Sở Hữu</td>
                <td>Loại Xe</td>
                <td>Biển Số Xe</td>
                <td>Chỗ Đặt</td>
                <td>Thời Gian Đặt Chỗ</td>
                <td>Action</td>
            </tr>
            <c:forEach var="booking" items="${listCheckoutBooking}">
                <tr valign="top">
                    <td>${booking.id}</td>
                    <td>${booking.vehicle.customer.fullname}</td>
                    <td>${booking.vehicle.type}</td>
                    <td>${booking.vehicle.licencePlate}</td>
                    <td>${booking.vacant.name}</td>
                    <td>${booking.checkinFormat}</td>
                    <td><a href="confirmCheckout?booking_id=${booking.id}">Chọn</a></td>
                </tr>
            </c:forEach>
        </table>    
    </body>
</html>
