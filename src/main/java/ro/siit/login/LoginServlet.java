package ro.siit.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private CredentialsValidator credentialsValidator;

    @Override
    public void init() throws ServletException {
        super.init();
        credentialsValidator = new CredentialsValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/login/loginForm.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");

        if (credentialsValidator.checkCredentials(username,pwd)){
            req.getSession().setAttribute("authenticated",true);
            resp.sendRedirect(req.getContextPath() + "/entity");
        }
        else{
            req.setAttribute("error", "Username/Password combination incorrect.");
            req.getRequestDispatcher("/jsps/login/loginForm.jsp").forward(req,resp);
        }


    }
}
