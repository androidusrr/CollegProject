<%@ page import="java.sql.*, util.DBConnection" %>
<%
    String loanIdStr = request.getParameter("loan_id");
    if (loanIdStr != null) {
        int loanId = Integer.parseInt(loanIdStr);
        try {
            Connection conn = DBConnection.getConnection();
            String updateQuery = "UPDATE loan_applications SET loan_status='approved' WHERE loan_id=?";
            PreparedStatement ps = conn.prepareStatement(updateQuery);
            ps.setInt(1, loanId);
            int rows = ps.executeUpdate();
            ps.close();
            conn.close();

            if (rows > 0) {
                response.sendRedirect("admin_index.jsp"); // back to admin dashboard
            } else {
                out.println("Failed to approve loan ID " + loanId);
            }
        } catch(Exception e) {
            out.println("Error: " + e.getMessage());
        }
    } else {
        out.println("Invalid Loan ID");
    }
%>
