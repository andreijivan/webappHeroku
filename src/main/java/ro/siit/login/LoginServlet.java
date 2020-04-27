package ro.siit.login;

import ro.siit.login.service.UserService;
import ro.siit.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("display", "none");
        req.getRequestDispatcher("/jsps/login/loginForm.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");
        User authenticatedUser = userService.checkCredentials(username,pwd);
        if (authenticatedUser !=null){
            req.getSession().setAttribute("authenticatedUser",authenticatedUser);
            resp.sendRedirect(req.getContextPath() + "/entity");
        }
        else{
            req.setAttribute("error", "Username/Password combination incorrect.");
            req.setAttribute("display", "block");
            req.getRequestDispatcher("/jsps/login/loginForm.jsp").forward(req,resp);
        }


    }
}
