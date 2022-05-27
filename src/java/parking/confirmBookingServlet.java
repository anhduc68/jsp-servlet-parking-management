package parking;

import business.Booking;
import business.Ticket;
import business.User;
import business.Vacant;
import business.Vehicle;
import data.BookingDAO;
import data.TicketDAO;
import data.VacantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class confirmBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get All atribute of this Booking
        String vacant_id = request.getParameter("vacant_id");
        Vacant vacant = VacantDAO.getVacantByID(Integer.parseInt(vacant_id));
        Vehicle vehicle = (Vehicle) request.getSession().getAttribute("vehicle");
        User user = (User) request.getSession().getAttribute("user");
        //set atributes of this Booking
        Booking booking = new Booking();
        booking.setIsCheckin(1);
        booking.setUser(user);
        booking.setVacant(vacant);
        booking.setVehicle(vehicle);
        booking.setCheckin(new Date());
        booking.formatCheckin();
        booking.setIsCheckout(0);
        booking.setPrice(booking.getVacant().getPrice());
        String msg = "Vé thường";
        String countCheckinMsg = "";
//         // check has monthTicket
        int hasMonthTicket = TicketDAO.hasMonthTicket(vehicle);
        if (hasMonthTicket == 1) {
            try {
                Ticket ticket = TicketDAO.getTicketByVehicle(vehicle);
                int count = BookingDAO.countCheckinInADay(vehicle);
                if (count <= 2) {
                    booking.setPrice(0);

                } else {
                    booking.setPrice(3000);

                }
                countCheckinMsg = "Số lượt gửi xe trong ngày: " + count;
                booking.setTicket(ticket);
                msg = "Vé tháng";
                request.setAttribute("msg", msg);
                request.setAttribute("countCheckinMsg", countCheckinMsg);
            } catch (ParseException ex) {
                Logger.getLogger(confirmBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } // if don't has Month Ticket --> Create Ticket
        else {
            Ticket ticket = new Ticket();
            ticket.setUser(user);
            ticket.setVehicle(vehicle);
            ticket.setPrice(vacant.getPrice());
            if (TicketDAO.addTurnTicket(ticket) == 1) {
                booking.setTicket(ticket);
                msg = "Vé thường";
                request.setAttribute("msg", msg);
            }
        }

//        
        //forward
        String url = "/confirmBooking.jsp";
        HttpSession session = request.getSession();
        session.setAttribute("booking", booking);
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Booking booking = (Booking) request.getSession().getAttribute("booking");
        //save to db
        String url = "";
        if (booking.getId() != 0) {
            if (BookingDAO.checkinBooking(booking) == 1) {
                url = "/carKeeperHome.jsp";
            }
        } else {
            if (BookingDAO.insert(booking) == 1) {
                url = "/carKeeperHome.jsp";
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("booking", booking);
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
