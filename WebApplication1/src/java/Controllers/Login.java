package Controllers;

import DAL.DAO;
import Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String u = request.getParameter("name");
        String p = request.getParameter("pass");

       
        if (u == null || p == null || u.isEmpty() || p.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.");
            request.getRequestDispatcher("Views/Login/login.jsp").forward(request, response);
            return;
        } 
        User us = new DAO().checkLogin(u, p);
        if(us == null){
            request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu ");
             request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
        }else if(us.isLocked()){
            request.setAttribute("error", "Tài khoản của bạn đã bị khóa do vi phạm chính sách của nhà phát triển ");
             request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
        }else{
              HttpSession session = request.getSession();
            session.setAttribute("user", us);
            if (us.getRole() == 1) {
              response.sendRedirect(request.getContextPath() + "/Views/Admin/dashboard.jsp");

            } else {
                response.sendRedirect("user/home.jsp");
            }
        }
      
    }
}
