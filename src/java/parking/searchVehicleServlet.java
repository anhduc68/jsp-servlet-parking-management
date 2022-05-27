
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

public class searchVehicleServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //getList<Vehicle
        ArrayList<Vehicle> listVehicle = VehicleDAO.getListVehicleToCheckin();
        request.setAttribute("listVehicle", listVehicle);
        
        //forward
        String url = "/searchVehicle.jsp";
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        //
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String licencePlate = request.getParameter("licencePlate");
         ArrayList<Vehicle> listVehicle = VehicleDAO.findVehicleByLicencePlate(licencePlate);
        request.setAttribute("listVehicle", listVehicle);
        
        //forward
        String url = "/searchVehicle.jsp";
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
        
    }
}
