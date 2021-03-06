package com.servlet;

import com.bean.HotelBean;
import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author AUSTINS
 */
public class RecoverPasswordController extends HttpServlet {

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

            String email_pat = "^[a-z]+[a-z.0-9-]+@[a-z.-]+(\\.[A-Za-z]{2,3})$";
            String email = request.getParameter("email");
            String pass = request.getParameter("pass1");
            String password = DigestUtils.sha256Hex(DigestUtils.md5Hex(pass));
           // out.println(password);
            if (Pattern.compile(email_pat).matcher(email).matches() || !email.equals("")) {
                HotelBean bean = new HotelBean();
                bean.setEmail(email);
                HotelHelper helper = new HotelHelper();
                try {
                    ResultSet rst = helper.recoverEmail(bean);
                    if (rst.next()) {
                        String query = "UPDATE admin SET password = '"+password+"' WHERE email = '"+email +"'";
                        PreparedStatement pst = helper.getConnection().prepareStatement(query);
                        int update = pst.executeUpdate();
                        if (update >0) {
                            
                        } else {
                            out.println("Password Reset Failed, try again in few minutes..");
                        }
                    } else {
                        out.println( email + " not found!!!");
                    }
                } catch (SQLException e) {
                }
            } else {
                out.println("Please enter a valid Email..");
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
