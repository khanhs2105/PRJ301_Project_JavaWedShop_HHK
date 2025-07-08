/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Registers;

import DAL.DAO;
import Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author USER
 */

public class Registers extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registers at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Registers/Registers.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String ageStr = request.getParameter("age");
        String genderStr = request.getParameter("gender");
        if (name == null || name.isEmpty() || password == null || password.isEmpty()
                || ageStr == null || ageStr.isEmpty() || genderStr == null || genderStr.isEmpty()) {
            request.setAttribute("error", "Không được để trống bất kỳ trường nào.");
            request.getRequestDispatcher("Views/Registers/Registers.jsp").forward(request, response);
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            boolean gender = Boolean.parseBoolean(genderStr);

            // Tạo User
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setAge(age);
            user.setGender(gender);
            user.setRole(0);           // mac dinh la 0(0: la user,1: la admin)
            user.setLocked(false);     // Chưa bị khóa

            boolean success = DAO.Ins.register(user);
            if (success) {
                response.sendRedirect("Login.jsp");
            } else {
                request.setAttribute("error", "Đăng ký thất bại. Tên có thể đã tồn tại.");
                request.getRequestDispatcher("Views/Registers/Registers.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Tuổi không hợp lệ.");
            request.getRequestDispatcher("Views/Registers/Registers.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("Views/Registers/Registers.jsp").forward(request, response);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
