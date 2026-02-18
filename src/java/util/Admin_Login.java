/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
package util;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import util.DBConnection;

public class Admin_Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try(Connection conn = DBConnection.getConnection()) {
          
            String sql = "SELECT admin_id FROM admin WHERE email = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Admin valid
                int adminId = rs.getInt("admin_id");

                HttpSession session = request.getSession();
                session.setAttribute("admin_id", adminId);
                session.setAttribute("admin_email", email);

                response.sendRedirect("admin_index.jsp");
            } else {
                // Invalid login
                response.sendRedirect("admin_login.jsp?error=invalid");
            }


        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin_login.jsp?error=server");
        }
    }
}
