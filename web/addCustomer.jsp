
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1>Thêm Khách Hàng</h1>
        <form action="addCustomerServlet" method ="Post">
            <label>Nhập Họ Và Tên: </label>
            <input type="text" name="fullname" value="${customer.fullname}" required=""><br/>
            <label>Nhập Địa Chỉ:  </label>
            <input type="text" name="address" value="${customer.address}" required=""><br/>
             <label>Nhập Số Điện Thoại:  </label>
            <input type="text" name="tel" value="${customer.tel}" required=""><br/>
            <label style="font: italic"> ${msg} </label><br/>
            <input type="submit" name="Thêm">
        </form>
    </body>
</html>
