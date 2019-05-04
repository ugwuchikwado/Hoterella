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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author AUSTINS
 */
public class RegisterAdmin extends HttpServlet {

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
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String responsibility = request.getParameter("responsibility");
            String pass = request.getParameter("password");

            String emailPattrn = "^[a-z]+[a-z.0-9-]+@[a-z.-]+(\\.[A-Za-z]{2,3})$";
            String passwordpattern = "([a-z\\.A-Z0-9:#$@%_]){8,15}";

            if (!Pattern.compile(emailPattrn).matcher(email).matches() || email.isEmpty()) {
                out.println("Invalid Email");
            } else {
                if (!Pattern.compile(passwordpattern).matcher(pass).matches() || pass.isEmpty()) {
                    out.println("Invalid Password");
                } else {
                    HotelHelper helper = new HotelHelper();
                    HotelBean bean = new HotelBean();
                    bean.setEmail(email);
                    ResultSet rst = helper.emailExist(bean);
                    try {
                        if (rst.next()) {
                            out.println("Email already exist");
                        } else {
                            String password = DigestUtils.sha256Hex(DigestUtils.md5Hex(pass));
                            HotelBean newBean = new HotelBean(name, email, password, responsibility);
                            int returncode = helper.adminData(newBean);

                            if (returncode == 1) {
                                out.println("Admin created Successfully");
                            } else {
                                out.println("Admin creation failed");
                            }
                        }
                    } catch (SQLException e) {
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
