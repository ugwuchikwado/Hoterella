
package com.servlet;

import com.bean.Food;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author AUSTINS
 */
public class PurchaseFoodController extends HttpServlet {

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
            String productID = request.getParameter("productID");
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            String quantity = request.getParameter("quantity");
            int unitprice = Integer.parseInt(request.getParameter("unitprice"));           
            String purchasedate = request.getParameter("purchasedate");
           String checkedby = (String)request.getSession().getAttribute("name");
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            SessionFactory sessionFactory = config.buildSessionFactory();
            Session session = sessionFactory.openSession();
            
            try {
                Transaction transaction = session.beginTransaction();
                Food food = new Food(productID, name, category, quantity, unitprice, purchasedate, checkedby);
                session.save(food);
                out.println("Action Successful");
                session.flush();
                transaction.commit();
            } catch (HibernateException e) {
            }finally{
                session.close();
                sessionFactory.close();
            }
           
//            newBean bean = new newBean(name, category, quantity, unitprice, totalprice, purchasedate);
//            HotelHelper helper = new HotelHelper();
//            int returnCode = helper.purchaseFood(bean);
//            if (returnCode == 1) {
//                
//            } else {
//                out.println("Action Failed");
//            }
//            out.println("Action Succesful");
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
