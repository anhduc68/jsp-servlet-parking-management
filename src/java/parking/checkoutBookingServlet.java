package parking;

import business.Booking;
import data.BookingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class checkoutBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<Booking> listBooking = BookingDAO.getListBookingToCheckout();
            request.setAttribute("listCheckoutBooking", listBooking);
            String url = "/checkout.jsp";
            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(searchBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
