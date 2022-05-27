/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parking;

import business.Customer;
import business.User;
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
public class checkLoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String url = "/index.jsp";
        String msg="";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if( UserDAO.checkLogin(user)!=1 ){
           msg = "Tài Khoản/Mật khẩu không chính xác";
        }
        else{
            if( user.getPosition().equalsIgnoreCase("carKeeper"))
                url = "/carKeeperHome.jsp";
            else 
                url = "/managerHome.jsp";
        }
         //forward to url
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        request.setAttribute("msg", msg);
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
