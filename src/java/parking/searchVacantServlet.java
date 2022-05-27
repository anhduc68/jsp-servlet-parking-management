package parking;

import business.Booking;
import business.Ticket;
import business.User;
import business.Vacant;
import business.Vehicle;
import data.TicketDAO;
import data.VacantDAO;
import data.VehicleDAO;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class searchVacantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get Vehicle
        String vehicle_id = request.getParameter("vehicle_id");
        Vehicle v = VehicleDAO.getVehicleID(Integer.parseInt(vehicle_id));
        String url = "";
        HttpSession session = request.getSession();
        session.setAttribute("vehicle", v);
        // check hasVIPticket
        if (TicketDAO.hasVipTicket(v) == 1) {
            try {
                Ticket ticket = TicketDAO.getTicketByVehicle(v);
                int vacant_id = ticket.getVacant().getId();
                Vacant vacant = VacantDAO.getVacantByID(vacant_id);
                request.setAttribute("vacant_id", vacant_id);
                Vehicle vehicle = (Vehicle) request.getSession().getAttribute("vehicle");
                User user = (User) request.getSession().getAttribute("user");
                //set atributes of this Booking
                Booking booking = new Booking();
                booking.setIsCheckin(1);
                booking.setIsCheckout(0);
                booking.setUser(user);
                booking.setVacant(vacant);
                booking.setVehicle(vehicle);
                booking.setCheckin(new Date());
                booking.setPrice(0);
                booking.setTicket(ticket);
                String msg = "Vé Víp";
                url = "/confirmBooking.jsp";
                session.setAttribute("booking", booking);
                request.setAttribute("msg", msg);
                RequestDispatcher dispatcher
                        = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);

            } catch (ParseException ex) {
                Logger.getLogger(searchVacantServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ArrayList<Vacant> listVacant = VacantDAO.getListVacantToCheckin(v.getType());
            request.setAttribute("listVacant", listVacant);
            //forward
            url = "/searchVacant.jsp";
        }
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
