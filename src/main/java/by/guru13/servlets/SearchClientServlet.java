package by.guru13.servlets;

import by.guru13.store.ClientCashe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ASUS on 12.08.2015.
 */
@WebServlet(name = "SearchClientServlet", urlPatterns = "/search")
public class SearchClientServlet extends HttpServlet {
    private ClientCashe clinic = ClientCashe.getInstance();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", clinic.findByName(req.getParameter("search")));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/ClinicView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        clinic.close();
    }
}
