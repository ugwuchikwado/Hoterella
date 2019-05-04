package com.servlet;

import com.bean.Event;
import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
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
public class EventCashControler extends HttpServlet {

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

            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String phonenumber = request.getParameter("phonenumber");
            Date eventdate = Date.valueOf(request.getParameter("eventdate"));
            String eventstarttime = request.getParameter("eventstarttime");
            String eventendtime = request.getParameter("eventendtime");
            String hallnumber = request.getParameter("hallnumber");
            Double hallprice = Double.parseDouble(request.getParameter("hallprice"));
            String checkedby = request.getParameter("checkedby");
            String paymentConfirmation = "not confirmed";
            String paymentMethod = request.getParameter("payment_method");

            String intitial = "HDR/";
            Random rand = new Random();
            String id = intitial.concat(String.valueOf(rand.nextInt(999999)));

            int hr = Integer.parseInt(eventstarttime.substring(0, 2));
            int min = Integer.parseInt(eventendtime.substring(3));
            LocalTime localTime = LocalTime.of(hr, min);

            HttpSession httpSession = request.getSession();           
            httpSession.setAttribute("id", id);
            httpSession.setAttribute("eventdate", eventdate.toLocalDate());
            httpSession.setAttribute("endTime", eventendtime);
            httpSession.setAttribute("type", "eventcash");            
            httpSession.setAttribute("event", fname + "_event");

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
            Session session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();
            try {
                HotelHelper helper = new HotelHelper();
                String query = "SELECT event_stop_time FROM event WHERE hall_number = '" + hallnumber + "'";
                PreparedStatement pst = helper.getConnection().prepareStatement(query);
                ResultSet rst = pst.executeQuery();

                if (rst.next()) {
                    String endTime = rst.getString("event_stop_time");
                    List<String> timeList = new ArrayList<>();
                    timeList.add(endTime);

                    for (int i = 0; i <= timeList.size(); i++) {
                        String get = timeList.get(i);
                        int hour = Integer.parseInt(get.substring(0, 2)) + 1;
                        int minute = Integer.parseInt(get.substring(3));
                        LocalTime lt = LocalTime.of(hour, minute);
                        if (localTime.isBefore(lt)) {
                            out.println(hallnumber + "is not available at your specified time. It'll be available at: " + lt);
                        } else {

                            Event event = new Event(id, fname, lname, email, phonenumber, eventdate, eventstarttime, eventendtime, hallnumber, hallprice, paymentConfirmation, checkedby, paymentMethod);
                            session.save(event);
                            session.flush();
                            transaction.commit();
                            out.println("Inserted Successfully");
                        }
                    }
                } else {
                    Event event = new Event(id, fname, lname, email, phonenumber, eventdate, eventstarttime, eventendtime, hallnumber, hallprice, paymentConfirmation, checkedby, paymentMethod);
                    session.save(event);
                    session.flush();
                    transaction.commit();
                    out.println("Inserted Successfully");
                }
            } catch (NumberFormatException | SQLException | HibernateException e) {
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
