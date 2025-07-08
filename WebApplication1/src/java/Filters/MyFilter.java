package Filters;

import Models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String url = req.getRequestURI();

        
        if (url.endsWith("/login") || url.contains("login.jsp") || url.endsWith("/register")) {
            chain.doFilter(request, response);
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int role = user.getRole(); 

      
         // Nếu user thường mà cố vào admin
        if (role == 0 && url.contains("/admin/")) {
            res.sendRedirect(req.getContextPath() + "/user/home.jsp");
            return;
        }

        // Nếu admin mà cố vào user
        if (role == 1 && url.contains("/user/")) {
            res.sendRedirect(req.getContextPath() + "/admin/dashboard.jsp");
            return;
        }

        // Nếu đúng role thì tiếp tục
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
