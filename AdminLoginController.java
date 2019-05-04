package com.servlet;

import com.bean.HotelBean;
import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author AUSTINS
 */
public class AdminLoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();

            String responsibility = request.getParameter("responsibility");
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
           
            String password = DigestUtils.sha256Hex(DigestUtils.md5Hex(pass));
            HotelBean bean = new HotelBean(email, password, responsibility);
            HotelHelper helper = new HotelHelper();

            session.setAttribute("msg", responsibility);
            ResultSet rst;
            try {
                rst = helper.loginAdmin(bean);
                if (rst.next()) {
                    String name = rst.getString("name");
                    session.setAttribute("name", name);
                    if (responsibility.equalsIgnoreCase("CEO")) {
                        session.setAttribute("msgCEO", email);
                        response.sendRedirect("jsp/admin.jsp");
                    } else if (responsibility.equalsIgnoreCase("Receptionist")) {
                        session.setAttribute("msgREC", email);
                        response.sendRedirect("jsp/front_desk_admin.jsp");
                    } else if (responsibility.equalsIgnoreCase("Beverage Manager")) {
                        session.setAttribute("msgBEV", email);
                        response.sendRedirect("jsp/beverage_admin.jsp");
                    } else if (responsibility.equalsIgnoreCase("Restaurant Manager")) {
                        session.setAttribute("msgREST", email);
                        response.sendRedirect("jsp/restaurant_admin.jsp");
                    } else {
                        session.setAttribute("msgAC", email);
                        response.sendRedirect("jsp/accountofficer.jsp");
                         session.setAttribute("pass", pass);
                    }
                } else {
                    session.setAttribute("error", "Wrong Email or Password");
                    response.sendRedirect("jsp/admin_login.jsp");
                }
            } catch (SQLException e) {
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
