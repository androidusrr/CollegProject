/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "INSERT INTO users (full_name, email, password) VALUES (?, ?, ?)";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, password);

            ps.executeUpdate();

            // SUCCESS → go to login page
            response.sendRedirect(request.getContextPath() + "/login_index.html");

        } catch (SQLException e) {
            e.printStackTrace();

            // FAIL → show error
            response.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Registration failed due to database error"
            );
        }
    }
}