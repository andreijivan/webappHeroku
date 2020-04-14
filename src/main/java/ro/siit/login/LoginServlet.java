package ro.siit.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("username");
       String pwd = req.getParameter("password");

       if (userName.equals("admin") && pwd.equals("admin")){
           req.getSession().setAttribute("authenticated", true);
           resp.sendRedirect(req.getContextPath() + "/entity");
        }
       else {
           req.setAttribute("error", "Username/password incorrect");
           req.getRequestDispatcher("/jsps/;ogin/loginForm.jsp").forward(req,resp);
       }



    }
}
