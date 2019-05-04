package com.servlet;

import com.bean.Event;
import com.bean.HotelBean;
import com.bean.HotelHelper;
import com.bean.Reservation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author AUSTINS
 */
public class EventControler extends HttpServlet {

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
            //String id = request.getParameter("userid");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String phonenumber = request.getParameter("phonenumber");
            Date eventdate = Date.valueOf(request.getParameter("eventdate"));
            String eventstarttime = request.getParameter("eventstarttime");
            String eventendtime = request.getParameter("eventendtime");
            String hallnumber = request.getParameter("hallnumber");
            Double hallprice = Double.parseDouble(request.getParameter("hallprice"));
            String paymentConfirmation = "not confirmed";

            String intitial = "HDR/";
            Random rand = new Random();
            String id = intitial.concat(String.valueOf(rand.nextInt(999999)));

            int hr = Integer.parseInt(eventstarttime.substring(0, 2));
            int min = Integer.parseInt(eventstarttime.substring(3));
            LocalTime localTime = LocalTime.of(hr, min);

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("f", fname);
            httpSession.setAttribute("l", lname);
            httpSession.setAttribute("e", email);
            httpSession.setAttribute("p", phonenumber);
            httpSession.setAttribute("eventdate", eventdate.toLocalDate());
            httpSession.setAttribute("amount", hallprice * 100);
            httpSession.setAttribute("endTime", eventendtime);
            httpSession.setAttribute("event", fname + "_event");
            httpSession.setAttribute("id", id);
            httpSession.setAttribute("type", "event");

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
            Session session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();
            HotelHelper helper = new HotelHelper();
            try {
                HotelBean emailbean = new HotelBean();
                emailbean.setEmail(email);
                ResultSet emailset = helper.userEmailExist(emailbean);

                List<LocalTime> localTimeList = new ArrayList<>();

                if (emailset.next()) {
                    String query = "SELECT event_stop_time FROM event WHERE hall_number = '" + hallnumber + "'";
                    PreparedStatement pst = helper.getConnection().prepareStatement(query);
                    ResultSet rst = pst.executeQuery();

                    while (rst.next()) {
                        String endTime = rst.getString("event_stop_time");

                        int hour = Integer.parseInt(endTime.substring(0, 2)) + 1;
                        int minute = Integer.parseInt(endTime.substring(3));
                        LocalTime lt = LocalTime.of(hour, minute);
                        localTimeList.add(lt);
                        return;
                    }

                        Event event = new Event(id, fname, lname, email, phonenumber, eventdate, eventstarttime, eventendtime, hallnumber, hallprice, paymentConfirmation, "Network", "Card");
                        session.saveOrUpdate(event);
                        session.flush();
                        transaction.commit();
                    
                } else {
                    out.println("Email not found: Please use the same email you entered during registration");
                }

                Collections.sort(localTimeList);
                LocalTime local = localTimeList.get(localTimeList.size() - 1);
                
                

                if (localTime.isBefore(local)) {
                    out.println(hallnumber + " is not available at your specified time. It'll be available at: " + local);
                } else {

                    Event event = new Event(id, fname, lname, email, phonenumber, eventdate, eventstarttime, eventendtime, hallnumber, hallprice, paymentConfirmation, "Network", "Card");
                    session.saveOrUpdate(event);
                    session.flush();
                    transaction.commit();
                }

            } catch (SQLException | HibernateException e) {
                out.println(e.getMessage());
            } finally {
                session.close();
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
