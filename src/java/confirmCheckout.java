
import business.Booking;
import data.BookingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parking.checkoutBookingServlet;

/**
 *
 * @author Dieu Dai Hiep
 */
public class confirmCheckout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        String booking_id = request.getParameter("booking_id");
        try {
            Booking booking = BookingDAO.getBookingByID(Integer.parseInt(booking_id));
            if (BookingDAO.checkoutBooking(booking) == 1) {
                url = "checkoutBookingServlet";
            }
        } catch (ParseException ex) {
            Logger.getLogger(checkoutBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(url);

    }
}
