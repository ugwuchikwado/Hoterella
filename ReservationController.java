package com.servlet;

import com.bean.HotelBean;
import com.bean.HotelHelper;
import com.bean.Reservation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
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
public class ReservationController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request     
     * @param response     
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
//            String namePattern = "[^\\s][A-Za-z\\s]+";
//            //String numberPattern = "[\\d]{11}";
//            String emailPattrn = "^[a-z]+[a-z.0-9-]+@[a-z.-]+(\\.[A-Za-z]{2,3})$";
           // String id = request.getParameter("userid");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String phonenumber = request.getParameter("phonenumber");
            Date check_in_date = Date.valueOf(request.getParameter("check_in_date"));
            String check_in_time = request.getParameter("check_in_time");
            Date check_out_date = Date.valueOf(request.getParameter("check_out_date"));
            String check_out_time = "12:00";
            String medical_condition = request.getParameter("medical_condition");
            String room_position = request.getParameter("room_position");
            String room_category = request.getParameter("room_category");
            String roomNo = request.getParameter("roomNo");
            double payable_amount = Double.parseDouble(request.getParameter("payable_amount"));

            int hr = Integer.parseInt(check_in_time.substring(0, 2));
            int min = Integer.parseInt(check_in_time.substring(3));
            LocalTime localTime = LocalTime.of(hr, min);
            // out.println(localTime);
            
            String intitial = "HDR/";
            Random rand = new Random();
            String id = intitial.concat(String.valueOf(rand.nextInt(999999)));

            HotelBean emailbean = new HotelBean();
            emailbean.setEmail(email);
            HotelHelper emailhelper = new HotelHelper();
            ResultSet emailset = emailhelper.userEmailExist(emailbean);
            try {
                if (emailset.next()) {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("f", fname);
                    httpSession.setAttribute("l", lname);
                    httpSession.setAttribute("e", email);
                    httpSession.setAttribute("p", phonenumber);
                    httpSession.setAttribute("amount", payable_amount * 100);
                    httpSession.setAttribute("date", check_out_date.toLocalDate());
                    httpSession.setAttribute("time", check_out_time);
                    httpSession.setAttribute("event", fname + "_event");
                    httpSession.setAttribute("id", id);
                    httpSession.setAttribute("type", "reservation");

                    Configuration configuration = new Configuration();
                    configuration.configure("hibernate.cfg.xml");
                    StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                    SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
                    Session session = sessionFactory.openSession();

                    Transaction transaction = session.beginTransaction();

                    HotelBean bean = new HotelBean();
                    bean.setRoomnumber(roomNo);
                    HotelHelper helper = new HotelHelper();
                    ResultSet set = helper.roomNumber(bean);
                    try {
                        if (set.next()) {
                            Date outDate = set.getDate("check_out_date");
                            String outTime = set.getString("check_out_time");

                            List<Date> myList = new ArrayList<>();
                            myList.add(outDate);
                            List<String> timeList = new ArrayList<>();
                            timeList.add(outTime);

                            Iterator iterator = myList.iterator();

                            while (iterator.hasNext()) {
                                Date dbDate = (Date) iterator.next();
                                if (check_in_date.compareTo(dbDate) == 0) {
                                    for (int i = 0; i <= timeList.size(); i++) {
                                        String get = timeList.get(i);
                                        int hour = Integer.parseInt(get.substring(0, 2)) + 1;
                                        int minute = Integer.parseInt(get.substring(3));
                                        LocalTime lt = LocalTime.of(hour, minute);
                                        if (localTime.isBefore(lt)) {
                                            out.println("Room already occupied by another Person. It'll be available on: " + dbDate + "at: " + lt);
                                        } else {
                                            Reservation reservation = new Reservation(id, fname, lname, email, phonenumber, check_in_date, check_in_time, check_out_date, check_out_time, medical_condition, payable_amount, room_category, room_position, roomNo, "not Confirmed","Network", "Card");
                                            session.saveOrUpdate(reservation);
                                            session.flush();
                                            transaction.commit();

                                        }
                                    }
                                } else {
                                    Reservation reservation = new Reservation(id, fname, lname, email, phonenumber, check_in_date, check_in_time, check_out_date, check_out_time, medical_condition, payable_amount, room_category, room_position, roomNo, "not Confirmed","Network", "Card");
                                    session.saveOrUpdate(reservation);
                                    session.flush();
                                    transaction.commit();

                                }

                            }
                        } else {
                            Reservation reservation = new Reservation(id, fname, lname, email, phonenumber, check_in_date, check_in_time, check_out_date, check_out_time, medical_condition, payable_amount, room_category, room_position, roomNo, "not Confirmed", "Network", "Card");
                            session.saveOrUpdate(reservation);
                            session.flush();
                            transaction.commit();

                        }
                    } catch (NumberFormatException | SQLException | HibernateException e) {

                    } finally {
                        session.close();
                        sessionFactory.close();
                    }
                } else {
                    out.println("Email not found: Please use the same email you entered during registration");
                }
            } catch (SQLException | HibernateException  ex) {
            }

            //out.println("ok");
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
