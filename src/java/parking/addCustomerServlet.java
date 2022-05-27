/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parking;

import business.Customer;
import business.User;
import data.CustomerDAO;
import data.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dieu Dai Hiep
 */
public class addCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        String msg = "";
        //
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //get parameter
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");

        Customer customer = new Customer();
        customer.setFullname(fullname);
        customer.setAddress(address);
        customer.setTel(tel);

        // valid 
        if (fullname.trim().equals("") || address.trim().equals("")
                || tel.trim().equals("") || fullname == null || address == null || tel == null) {
            url = "/addCustomer.jsp";
            msg = "Vui lòng điền đủ thông tin!";
            //forward to url
            request.setAttribute("customer", customer);
            request.setAttribute("msg", msg);
            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            if (CustomerDAO.insert(customer) == 1) {
                url = "addVehicleServlet";
                response.sendRedirect(url);
            }
        }

    }
}
