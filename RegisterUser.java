package com.servlet;

import com.bean.HotelBean;
import com.bean.HotelHelper;
import com.bean.UserData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author AUSTINS
 */
public class RegisterUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static class SMTPauthenticator extends Authenticator {

        public PasswordAuthentication getpassAuthetication() {
            return new PasswordAuthentication("austillo247@gmail.com", "joe042SAP:IgwE");
        }
    ;

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String namePattern = "[^\\s][A-Za-z\\s]+";
            String numberPattern = "[\\d]{11}";
            String emailPattrn = "^[a-z]+[a-z.0-9-]+@[a-z.-]+(\\.[A-Za-z]{2,3})$";
            String passwordpattern = "([a-z\\.A-Z0-9:#$@%_]){8,15}";

            String name = request.getParameter("name");

            String email = request.getParameter("email");

            String phonenumber = request.getParameter("phonenumber");

            String address = request.getParameter("address");

            String gender = request.getParameter("gender");

            String nextOfKin = request.getParameter("nextOfKin");

            String nextOfKinPhone = request.getParameter("nextOfKinPhone");

            String password = DigestUtils.md5Hex(request.getParameter("password")).concat(DigestUtils.sha256Hex(request.getParameter("password")));
            Date registerDate = new Date(new java.util.Date().getTime());

            Random rand = new Random();
            rand.nextInt(999999);
            String hash = DigestUtils.md5Hex("" + rand);
            
             String intitial = "HDR/";           
            String id = intitial.concat(String.valueOf(rand.nextInt(999999)));

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("to", email);
            httpSession.setAttribute("hash", hash);

            //out.println(name + email + phonenumber + address + gender + nextOfKin + nextOfKinPhone + password + registerDate +"," +newPassword +"," +hash);
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
            Session sec = sessionFactory.openSession();
            try {
                Transaction transaction = sec.beginTransaction();

                HotelBean bean = new HotelBean();
                bean.setEmail(email);
                HotelHelper helper = new HotelHelper();
                ResultSet rst = helper.userEmailExist(bean);
                if (rst.next()) {
                    out.println("Email already Exist....");
                } else {
                    UserData userData = new UserData(id, name, email, phonenumber, address, gender, nextOfKin, nextOfKinPhone, password, registerDate, hash, 0);

                    sec.saveOrUpdate(userData);
                    sec.flush();
                    transaction.commit();

                    // out.println("ok");
                }

            } catch (SQLException | HibernateException e) {
            } finally {
                sec.close();
                sessionFactory.close();
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
