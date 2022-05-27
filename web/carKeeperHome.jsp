<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml xmlns:th="http://www.thymeleaf.org">

    <head>
        <title>Quản Lý Bãi Đỗ Xe</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="styles/style.css" type="text/css"/>
        <style>
            body {
                height: 80vh;
                background: url("https://images.unsplash.com/photo-1543465077-db45d34b88a5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1965&q=80") no-repeat center center/cover;
            }
        </style>
    </head>

    <body>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item mx-2">
                        <a class="nav-link" aria-current="page" href="/parkingmanagement/carKeeperHome.jsp">Trang Chủ</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="nav-link" aria-current="page" href="searchBookingServlet">Checkin</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="nav-link" aria-current="page" href="checkoutBookingServlet">Checkout</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="nav-link" aria-current="page" href="/">Buy MonthTicket</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="nav-link" aria-current="page" href="/">Buy VipTicket</a>
                    </li>
                </ul>
            </div>
        </nav>

        <h1
            style="text-align: center; position: relative; top: 50%; color: #fff; font-size: 3rem; text-transform: uppercase;">
            Hệ Thống Quản Lý Bãi Đỗ Xe</h1>
    </body>

</html>