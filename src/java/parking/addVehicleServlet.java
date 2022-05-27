package parking;

import business.Customer;
import business.User;
import business.Vehicle;
import data.CustomerDAO;
import data.UserDAO;
import data.VehicleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class addVehicleServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //getList<Vehicle
        ArrayList<Customer> listCustomer = CustomerDAO.getList();
        request.setAttribute("listCustomer", listCustomer);

        //forward
        String url = "/addVehicle.jsp";
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        //
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "";
        String msg = "";
        //
        String type = request.getParameter("type");
        String licencePlate = request.getParameter("licencePlate");
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        Vehicle v = new Vehicle();
        v.setCustomer(CustomerDAO.getCustomerByID(customer_id));
        v.setLicencePlate(licencePlate);
        v.setType(type);

        //isExist
        if (VehicleDAO.isExist(licencePlate) == 1) {
            msg = "Biển số xe đã tồn tại!";
            url = "/addVehicle.jsp";
            request.setAttribute("vehicle", v);
            request.setAttribute("msg", msg);
            ArrayList<Customer> listCustomer = CustomerDAO.getList();
            request.setAttribute("listCustomer", listCustomer);
            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            if (VehicleDAO.insert(v) == 1) {
                url = "searchVehicleServlet";
                response.sendRedirect(url);
                
            }
        }

        //forward
    }
}
