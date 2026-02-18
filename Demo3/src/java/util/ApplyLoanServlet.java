//
//package util;
//
//import util.DBConnection;
//import java.sql.*;
//import java.io.IOException;
//import java.net.URLEncoder;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//
//public class ApplyLoanServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user_id") == null) {
//            response.sendRedirect("login_index.html");
//            return;
//        }
//
//        int userId = (int) session.getAttribute("user_id");
//            System.out.println("Loan amount parameter: '" + request.getParameter("loan_amount") + "'");
//
//        try {
//            // Parse numeric fields
//            double salary = parseDoubleParam(request, "salary", "Salary is required!");
//            double nomineeSalary = parseDoubleParam(request, "nominee_salary", "Nominee salary is required!");
//           // int loan_amount = parseIntParam(request, "loan_amount", "Loan amount is required!"); // optional, if you add loan_amount input
//
//            // Prepare SQL
//            String sql = "INSERT INTO loan_applications ("
//                    + "user_id, full_name, email, aadhaar, pan, salary, loan_amount, employment_type, "
//                    + "bank_account_number, bank_name, bank_district, bank_state, bank_pincode, cbin_number, "
//                    + "perm_house, perm_street, perm_city, perm_district, perm_state, perm_pincode, "
//                    + "curr_house, curr_street, curr_city, curr_district, curr_state, curr_pincode, "
//                    + "qualification, nominee_name, nominee_email, nominee_aadhaar, nominee_pan, "
//                    + "nominee_occupation, nominee_salary, nominee_address"
//                    + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//
//            try (Connection conn = DBConnection.getConnection();
//                 PreparedStatement ps = conn.prepareStatement(sql)) {
//
//                int i = 1;
//                ps.setInt(i++, userId);
//                ps.setString(i++, getParam(request, "full_name"));
//                ps.setString(i++, getParam(request, "email"));
//                ps.setString(i++, getParam(request, "aadhaar"));
//                ps.setString(i++, getParam(request, "pan"));
//                ps.setDouble(i++, salary);
//                ps.setInt(i++, Integer.parseInt(request.getParameter("loan_amount")));
//               // ps.setInt(i++, loan_amount); // comment out if not using loan_amount field
//                ps.setString(i++, getParam(request, "employment_type"));
//
//                ps.setString(i++, getParam(request, "bank_account_number"));
//                ps.setString(i++, getParam(request, "bank_name"));
//                ps.setString(i++, getParam(request, "bank_district"));
//                ps.setString(i++, getParam(request, "bank_state"));
//                ps.setString(i++, getParam(request, "bank_pincode"));
//                ps.setString(i++, getParam(request, "cbin_number"));
//
//                ps.setString(i++, getParam(request, "perm_house"));
//                ps.setString(i++, getParam(request, "perm_street"));
//                ps.setString(i++, getParam(request, "perm_city"));
//                ps.setString(i++, getParam(request, "perm_district"));
//                ps.setString(i++, getParam(request, "perm_state"));
//                ps.setString(i++, getParam(request, "perm_pincode"));
//
//                ps.setString(i++, getParam(request, "curr_house"));
//                ps.setString(i++, getParam(request, "curr_street"));
//                ps.setString(i++, getParam(request, "curr_city"));
//                ps.setString(i++, getParam(request, "curr_district"));
//                ps.setString(i++, getParam(request, "curr_state"));
//                ps.setString(i++, getParam(request, "curr_pincode"));
//
//                ps.setString(i++, getParam(request, "qualification"));
//
//                ps.setString(i++, getParam(request, "nominee_name"));
//                ps.setString(i++, getParam(request, "nominee_email"));
//                ps.setString(i++, getParam(request, "nominee_aadhaar"));
//                ps.setString(i++, getParam(request, "nominee_pan"));
//                ps.setString(i++, getParam(request, "nominee_occupation"));
//                ps.setDouble(i++, nomineeSalary);
//                ps.setString(i++, getParam(request, "nominee_address"));
//
//                //ps.setString(i++, "PENDING"); // loan_status default
//
//                int rows = ps.executeUpdate();
//
//                if (rows > 0) {
//                    // Save name/email in session for dashboard
//                    session.setAttribute("full_name", getParam(request, "full_name"));
//                    session.setAttribute("email", getParam(request, "email"));
//                    response.sendRedirect("dashboard_index.jsp");
//                } else {
//                    redirectWithError(response, "Loan application failed!");
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            redirectWithError(response, e.getMessage());
//        }
//    }
//
//    // Helper to get non-empty parameter
//    private String getParam(HttpServletRequest request, String name) throws Exception {
//        String value = request.getParameter(name);
//        if (value == null || value.trim().isEmpty()) {
//            throw new Exception(name + " is required!");
//        }
//        return value.trim();
//    }
//
//    // Helper to parse doubles safely
//    private double parseDoubleParam(HttpServletRequest request, String name, String errorMsg) throws Exception {
//        String value = request.getParameter(name);
//        if (value == null || value.trim().isEmpty()) {
//            throw new Exception(errorMsg);
//        }
//        try {
//            return Double.parseDouble(value.trim());
//        } catch (NumberFormatException ex) {
//            throw new Exception(name + " must be a valid number!");
//        }
//    }
//
//    // Helper to parse integers safely
//    private int parseIntParam(HttpServletRequest request, String name, String errorMsg) throws Exception {
//        String value = request.getParameter(name);
//        if (value == null || value.trim().isEmpty()) {
//            throw new Exception(errorMsg);
//        }
//        try {
//            return Integer.parseInt(value.trim());
//        } catch (NumberFormatException ex) {
//            throw new Exception(name + " must be a valid integer!");
//        }
//    }
//
//    // Redirect with error message
//    private void redirectWithError(HttpServletResponse response, String errorMsg) throws IOException {
//        response.sendRedirect("applyloan_index.html?error=" + URLEncoder.encode(errorMsg, "UTF-8"));
//    }
//}

package util;

import java.sql.*;
import java.io.IOException;
import java.net.URLEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class ApplyLoanServlet extends HttpServlet {

    /*
     * ApplyLoanServlet
     * ----------------
     * Inserts full loan application into loan_applications table.
     *
     * Table handles automatically:
     * - loan_id (AUTO_INCREMENT)
     * - interest_rate (DEFAULT 11.50)
     * - loan_status (DEFAULT 'PENDING')
     * - applied_at (CURRENT_TIMESTAMP)
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("login_index.html");
            return;
        }

        int userId = (int) session.getAttribute("user_id");

        try {

            double salary = parseDoubleParam(request, "salary", "Salary required");
            double nomineeSalary = parseDoubleParam(request, "nominee_salary", "Nominee salary required");
            int loanAmount = Integer.parseInt(request.getParameter("loan_amount"));

            String sql = "INSERT INTO loan_applications ("
                    + "user_id, full_name, email, aadhaar, pan, salary, loan_amount, loan_category, "
                    + "employment_type, bank_account_number, bank_name, bank_district, bank_state, "
                    + "bank_pincode, cbin_number, perm_house, perm_street, perm_city, perm_district, "
                    + "perm_state, perm_pincode, curr_house, curr_street, curr_city, curr_district, "
                    + "curr_state, curr_pincode, qualification, nominee_name, nominee_email, "
                    + "nominee_aadhaar, nominee_pan, nominee_occupation, nominee_salary, nominee_address"
                    + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                int i = 1;

                ps.setInt(i++, userId);
                ps.setString(i++, getParam(request, "full_name"));
                ps.setString(i++, getParam(request, "email"));
                ps.setString(i++, getParam(request, "aadhaar"));
                ps.setString(i++, getParam(request, "pan"));
                ps.setDouble(i++, salary);
                ps.setInt(i++, loanAmount);

                // Loan category from dropdown
                ps.setString(i++, request.getParameter("loan_category"));

                ps.setString(i++, getParam(request, "employment_type"));
                ps.setString(i++, getParam(request, "bank_account_number"));
                ps.setString(i++, getParam(request, "bank_name"));
                ps.setString(i++, getParam(request, "bank_district"));
                ps.setString(i++, getParam(request, "bank_state"));
                ps.setString(i++, getParam(request, "bank_pincode"));
                ps.setString(i++, getParam(request, "cbin_number"));

                ps.setString(i++, getParam(request, "perm_house"));
                ps.setString(i++, getParam(request, "perm_street"));
                ps.setString(i++, getParam(request, "perm_city"));
                ps.setString(i++, getParam(request, "perm_district"));
                ps.setString(i++, getParam(request, "perm_state"));
                ps.setString(i++, getParam(request, "perm_pincode"));

                ps.setString(i++, getParam(request, "curr_house"));
                ps.setString(i++, getParam(request, "curr_street"));
                ps.setString(i++, getParam(request, "curr_city"));
                ps.setString(i++, getParam(request, "curr_district"));
                ps.setString(i++, getParam(request, "curr_state"));
                ps.setString(i++, getParam(request, "curr_pincode"));

                ps.setString(i++, getParam(request, "qualification"));
                ps.setString(i++, getParam(request, "nominee_name"));
                ps.setString(i++, getParam(request, "nominee_email"));
                ps.setString(i++, getParam(request, "nominee_aadhaar"));
                ps.setString(i++, getParam(request, "nominee_pan"));
                ps.setString(i++, getParam(request, "nominee_occupation"));
                ps.setDouble(i++, nomineeSalary);
                ps.setString(i++, getParam(request, "nominee_address"));

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    response.sendRedirect("dashboard_index.jsp");
                } else {
                    redirectWithError(response, "Application failed");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectWithError(response, e.getMessage());
        }
    }

    private String getParam(HttpServletRequest request, String name) throws Exception {
        String value = request.getParameter(name);
        if (value == null || value.trim().isEmpty()) {
            throw new Exception(name + " is required");
        }
        return value.trim();
    }

    private double parseDoubleParam(HttpServletRequest request, String name, String msg) throws Exception {
        String value = request.getParameter(name);
        if (value == null || value.trim().isEmpty()) {
            throw new Exception(msg);
        }
        return Double.parseDouble(value.trim());
    }

    private void redirectWithError(HttpServletResponse response, String msg) throws IOException {
        response.sendRedirect("applyloan_index.html?error=" +
                URLEncoder.encode(msg, "UTF-8"));
    }
}
