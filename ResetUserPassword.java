package com.servlet;

import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AUSTINS
 */
public class ResetUserPassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static class SMTPauthentiator extends Authenticator {

        public PasswordAuthentication getpassAuthetication() {
            return new PasswordAuthentication("austillo247@gmail.com", "joe042SAP:IgwE");
        }
    ;

    }
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String email_pat = "^[a-z]+[a-z.0-9-]+@[a-z.-]+(\\.[A-Za-z]{2,3})$";
            String email = request.getParameter("email");
            if (Pattern.compile(email_pat).matcher(email).matches() || !email.equals("")) {
               
                try {
                     HotelHelper helper = new HotelHelper();
                String query = "SELECT email FROM user_data WHERE email = '" + email+"'";
                PreparedStatement pst = helper.getConnection().prepareStatement(query);
                ResultSet rst = pst.executeQuery();
               
                if (rst.next()) {
                    String message = "Your Verification Link:: " + "http://localhost:8080/Hotel_Management/jsp/resetemail.jsp?key=" + email;

                    String fromEmail = "austillo247@gmail.com";
                    String username = fromEmail;
                    String password = "joe042SAP:IgwE";

                    Properties prop = new Properties();//creating an object of properties class
                    ResetUserPassword.SMTPauthentiator auth = new ResetUserPassword.SMTPauthentiator();//creating an objet of the authentication class that will enable us gain access to our gmail account.

                    Session session = Session.getInstance(prop, auth);

                    MimeMessage msg = new MimeMessage(session);

                    
                        msg.setText(message);
                        msg.setSubject("Password Reset Email");
                        msg.setFrom(new InternetAddress(fromEmail));
                        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

                        Transport tr = session.getTransport("smtps");
                        tr.connect("smtp.gmail.com", 465, username, password);
                        tr.sendMessage(msg, msg.getAllRecipients());
                        tr.close();
                        out.println("Your reset link has been sent to your email..");
                    
                } else {
                    out.println("Email does not exist, check your email and try again");
                }
                } catch (SQLException | MessagingException e) {
                     out.println(e);
                }
               

            } else {
                out.println("Invalid Email");
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
