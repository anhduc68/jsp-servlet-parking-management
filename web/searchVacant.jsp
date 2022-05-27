
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
        <h1>Tìm Chỗ Trống: </h1>
         <table cellspacing="5" cellpadding="5" border="1">   
            <tr>
                <td>ID</td>
                <td>Tên</td>
                <td>Loại</td>
                <td>Diện Tích</td>
                <td>Giá</td>
                <td>Action</td>
            </tr>
            <c:forEach var="vacant" items="${listVacant}">
                <tr valign="top">
                    <td>${vacant.id}</td>
                    <td>${vacant.name}</td>
                    <td>${vacant.type}</td>
                    <td>${vacant.area}</td>
                    <td>${vacant.price}</td>
                    <td><a href="confirmBookingServlet?vacant_id=${vacant.id}">Chọn</a></td>
                </tr>
            </c:forEach>
        </table>    
            
    </body>
</html>
