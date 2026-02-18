<%@ page import="java.sql.*, util.DBConnection" %>
<%
    int loanId = Integer.parseInt(request.getParameter("loan_id"));

    try {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE loan_applications SET loan_status = 'rejected' WHERE loan_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, loanId);
        ps.executeUpdate();

        ps.close();
        conn.close();

        response.sendRedirect("admin_index.jsp");
    } catch(Exception e) {
        out.println("Error: " + e.getMessage());
    }
%>