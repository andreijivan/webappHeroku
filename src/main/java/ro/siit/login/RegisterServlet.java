package ro.siit.login;

import ro.siit.service.UserService;
import ro.siit.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("displayError", "none");
        req.setAttribute("displaySuccess", "none");
        req.getRequestDispatcher("/jsps/login/registerForm.jsp").forward(req,resp);

        String action = req.getParameter("action");
        if (action.equals("validate-username")){
            String username = req.getParameter("username");
           boolean exists = userService.usernameExists(username);
           resp.setContentType("application/json");
            resp.getWriter().println("{exists: " + exists + "}");
        } else {
        req.setAttribute("displayError", "none");
        req.setAttribute("displaySuccess", "none");
        req.getRequestDispatcher("/jsps/login/registerForm.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");

        boolean usernameExists = userService.usernameExists(username);
        if (!usernameExists){
            User user = new User(UUID.randomUUID(),username,pwd);
            userService.registerUser(user);
            req.setAttribute("displaySuccess","block");
            req.setAttribute("displayError","none");
            req.setAttribute("success", "User registered successfully.");
        }
        else{
            req.setAttribute("displayError","block");
            req.setAttribute("displaySuccess","none");
            req.setAttribute("error", "Username already used.");
        }
        req.getRequestDispatcher("/jsps/login/registerForm.jsp").forward(req,resp);


    }
}
