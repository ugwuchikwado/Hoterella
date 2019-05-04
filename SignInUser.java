package com.servlet;

import com.bean.HotelBean;
import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
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
public class SignInUser extends HttpServlet {

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

            String emailPattrn = "^[a-z]+[a-z.0-9-]+@[a-z.-]+(\\.[A-Za-z]{2,3})$";           

            if (request.getParameter("login") != null) {
                String email = request.getParameter("email");
                String password = DigestUtils.md5Hex(request.getParameter("password")).concat(DigestUtils.sha256Hex(request.getParameter("password")));
                
                session.setAttribute("email", email);
                if (!Pattern.compile(emailPattrn).matcher(email).matches() || email.isEmpty()) {
                    session.setAttribute("Msg", "Invalid Email");
                    response.sendRedirect("jsp/sign_in.jsp");
                } else {
                    if (password.isEmpty()) {
                        session.setAttribute("Msg", "Password Field cannot be empty");
                        response.sendRedirect("jsp/sign_in.jsp");
                    } else {
                        HotelBean bean = new HotelBean(email, password, 1);

                        HotelHelper helper = new HotelHelper();
                        ResultSet rst = helper.loginUser(bean);
                        try {

                            if (rst.next()) {
                                String phone = rst.getString("phonenumber");
                                String id = rst.getString("userid");
                                session.setAttribute("userid", id);
                                session.setAttribute("username", email);
                                session.setAttribute("phone", phone);
                                
                                response.sendRedirect("jsp/user_page.jsp");

                            } else {
                                session.setAttribute("Msg", "Invalid Email or Password");
                                response.sendRedirect("jsp/sign_in.jsp");

                            }
                        } catch (SQLException e) {
                        }
                    }
                }

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
