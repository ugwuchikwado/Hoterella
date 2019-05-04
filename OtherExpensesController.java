
package com.servlet;

import com.bean.OtherExpenses;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class OtherExpensesController extends HttpServlet {

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
              String expenseon = request.getParameter("expenseon");
           double amount = Double.parseDouble(request.getParameter("amount"));
           String dateString= request.getParameter("date");
           String split[] = dateString.split("/");
           
           LocalDate local = LocalDate.of(Integer.parseInt(split[2]), Integer.parseInt(split[0]), Integer.parseInt(split[1]));
           Date date = Date.valueOf(local);
           
          // out.print(date);
            try {
                 Configuration configuration = new Configuration();
                    configuration.configure("hibernate.cfg.xml");
                    StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                    SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
                    Session session = sessionFactory.openSession();

                    Transaction transaction = session.beginTransaction();
                    
                    OtherExpenses otherExpenses = new OtherExpenses(expenseon, amount, date);
                    session.save(otherExpenses);
                    session.flush();
                    transaction.commit();
                    
                    session.close();
                    sessionFactory.close();
            } catch (HibernateException e) {
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
