package parking;

import business.Booking;
import data.BookingDAO;
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

public class checkinBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String booking_id = request.getParameter("booking_id");
        Booking booking = new Booking();
        String url="/confirmBooking.jsp";
        try {
            booking = BookingDAO.getBookingByID(Integer.parseInt(booking_id));
        } catch (ParseException ex) {
            Logger.getLogger(checkinBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         //forward
       
        HttpSession session = request.getSession();
        session.setAttribute("booking", booking);
        String msg="Vé thuờng";
        request.setAttribute("msg", msg);
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
