package com.servlet;

import com.bean.HotelBean;
import com.bean.HotelHelper;
import com.bean.Room;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author AUSTINS
 */
public class CreateRoomController extends HttpServlet {

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
            String roomtype = request.getParameter("roomtype");
            String roomposition = request.getParameter("roomposition");
            String roomnumber = request.getParameter("roomnumber");
            double unitprice = Double.parseDouble(request.getParameter("unitprice"));
            String createdBy = (String)request.getSession().getAttribute("name");
              //out.println(roomtype + ","+ roomposition + ","+ roomnumber + ","+ unitprice + ","+createdBy);
            HotelBean bean = new HotelBean();

            HotelHelper helper = new HotelHelper();
            bean.setRoomnumber(roomnumber);
            ResultSet resultSet = helper.roomExist(bean);
            try {
                if (resultSet.next()) {
                    out.println("Room already Exist");
                } else {
                    
                    Configuration configuration = new Configuration();
                    configuration.configure("hibernate.cfg.xml");
                    StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                    SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
                    Session session = sessionFactory.openSession();

                    Transaction transaction = session.beginTransaction();
                    Room room = new Room(roomtype, roomposition, roomnumber, unitprice, createdBy);
                    
                    session.save(room);
                    session.flush();
                    transaction.commit();
                    session.close();
                    sessionFactory.close();
                   
                        out.println("Room created successfully");
                   
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
