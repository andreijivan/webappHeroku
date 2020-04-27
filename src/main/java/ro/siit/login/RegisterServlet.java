package ro.siit.login;

import ro.siit.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private CredentialsValidator credentialsValidator;

    @Override
    public void init() throws ServletException {
        super.init();
        credentialsValidator = new CredentialsValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("display", "none");
        req.getRequestDispatcher("/jsps/login/registerForm.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");
        User authenticatedUser = credentialsValidator.checkCredentials(username,pwd);
        if (authenticatedUser !=null){
            req.getSession().setAttribute("authenticatedUser",authenticatedUser);
            resp.sendRedirect(req.getContextPath() + "/entity");
        }
        else{
            req.setAttribute("error", "Username/Password combination incorrect.");
            req.getRequestDispatcher("/jsps/login/loginForm.jsp").forward(req,resp);
        }


    }
}
