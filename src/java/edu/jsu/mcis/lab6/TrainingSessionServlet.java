
package edu.jsu.mcis.lab6;

import edu.jsu.mcis.lab6.dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TrainingSessionServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json;charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            
            String s_id = request.getParameter("sessionid"); 
            TrainingSessionDAO dao = daoFactory.getTrainingSessionDAO();
            
            if (s_id == null || "".equals(s_id)) {
                System.err.println("Getting all sessions");
                out.println(dao.findSession());
            }
            else {
                System.err.println("Finding Attendees");
                out.println(dao.findSessionById(Integer.parseInt(s_id)));
            }
            
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Training Session Servlet";
    }

}
