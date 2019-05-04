package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
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
public class ReplyMessageController extends HttpServlet {

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
            String emailTo = request.getParameter("emailTo");
            String messageTitle = request.getParameter("messageTitle");
            String message = request.getParameter("message");
            //out.println(emailTo + messageTitle + message);

            String username = "austillo247@gmail.com";
            String pass = "joe042SAP:IgwE";
            String d_host = "smtp.gmail.com";
            int d_port = 465;

            try {
                Properties prop = new Properties();
                SMTPauthentiator auth = new SMTPauthentiator();

                Session session = Session.getInstance(prop, auth);

                MimeMessage msg = new MimeMessage(session);

                msg.setContent(message, "text/html");
                msg.setSubject(messageTitle);
                msg.setFrom(new InternetAddress(username));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

                Transport tr = session.getTransport("smtps");
                tr.connect(d_host, d_port, username, pass);
                tr.sendMessage(msg, msg.getAllRecipients());
                out.println("Message Successfully sent...");
            } catch (MessagingException e) {
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
