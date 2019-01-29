package servlet;

import command.ActionCommand;
import command.Factory;
import control.Configuration;
import control.TextLanguage;
import db.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/web")
public class MainController extends HttpServlet implements ServletContextListener {
    private static final String LOGIN_PAGE = "path.login";
    private static final String NULL_ATTRIBUTE = "nullPage";
    private static final String LOCALE_ATTRIBUTE = "localization";
    private static final String NULL_PAGE_MESSAGE = "message.login.nullPage";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().closePool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        SessionRequestContent requestContent = new SessionRequestContent();
        requestContent.extractValues(request);
        ActionCommand actionCommand = Factory.defineCommand(requestContent);
        page = actionCommand.execute(requestContent);
        requestContent.insertValues(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = Configuration.getProperties(LOGIN_PAGE);
            String pageMessage = TextLanguage.getText(NULL_PAGE_MESSAGE,
                    (String) request.getSession().getAttribute(LOCALE_ATTRIBUTE));
            request.getSession().setAttribute(NULL_ATTRIBUTE, pageMessage);
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
