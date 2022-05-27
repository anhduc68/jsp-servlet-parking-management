
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
        <h1>Confirm Booking</h1>
        <form action="confirmBookingServlet" method ="Post">
            <label>Tên khách hàng: </label>
            <label>${booking.vehicle.customer.fullname} </label><br/>
            <label>Biển Số Xe: </label>
            <label>${booking.vehicle.licencePlate}</label><br/>
            <label>Loại Xe: </label>
            <label>${booking.vehicle.type} </label><br/>
            <label>Chỗ Trống: </label>
            <label>${booking.vacant.name} </label><br/>
            <label>Mã Vé: </label>
            <label>${booking.ticket.id} </label><br/>
            <label>${msg} </label><br/>
            <label>${countCheckinMsg} </label><br/>
            <label>Giá Vé: </label>
            <label>${booking.price} </label><br/>
            <label>Thời Gian Checkin: </label>
            <label>${booking.checkinFormat} </label><br/>
            <input type="submit" value="Xác nhận">
        </form>
    </body>
</html>
