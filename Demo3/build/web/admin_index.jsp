<%@ page import="java.sql.*, util.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - RupeeLoans</title>
    <link rel="stylesheet" href="admin_style.css">
</head>
<body class="admin_body">

<header class="admin_header">
    <div class="company_logo">Rupee<span>Loans</span></div>
    <nav class="admin_nav">
        <a href="admin_index.jsp">All Loans</a>
       <a href="logout.html"><i class="fa-solid fa-right-from-bracket"></i> Log Out</a>
    </nav>
</header>

<section class="admin_dashboard">
    <h2>Loan Applications by Category</h2>

<%
String[] categories = {"Home Loan","Car Loan"
, "Personal Loan" , "Education Loan","Business Loan","Medical Loan"};

try(Connection conn = DBConnection.getConnection()) {

    for(String category : categories) {
%>

    <div class="loan_category_section">
        <h3><%= category.toUpperCase() %> LOANS</h3>

        <table class="admin_table">
            <thead>
                <tr>
                    <th>Loan ID</th>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>Applied On</th>
                    <th>Salary</th>
                    <th>Loan Amount</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>

<%
        String query = "SELECT * FROM loan_applications WHERE loan_category=? ORDER BY applied_at DESC";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, category);
        ResultSet rs = ps.executeQuery();

        boolean hasData = false;

        while(rs.next()) {
            hasData = true;

            int loanId = rs.getInt("loan_id");
            int userId = rs.getInt("user_id");
            String name = rs.getString("full_name");
            double salary = rs.getDouble("salary");
            double amount = rs.getDouble("loan_amount");
            String loanCategory = rs.getString("loan_category");
            Timestamp appliedAt = rs.getTimestamp("applied_at");
            String status = rs.getString("loan_status");

            String statusClass = status == null ? "pending" : status.trim().toLowerCase();
%>

                <tr>
                    <td><%= loanId %></td>
                    <td><%= userId %></td>
                    <td><%= name %></td>
                    <td><%= appliedAt %></td>
                    <td>Rs <%= String.format("%.2f", salary) %></td>
                    <td>Rs <%= String.format("%.2f", amount) %></td>
                    <td><%= loanCategory %></td>
                    <td class="status <%= statusClass %>"><%= status %></td>
                    <td>
                        <% if(statusClass.equals("pending")) { %>
                            <form action="approve_loan.jsp" method="post" style="display:inline;">
                                <input type="hidden" name="loan_id" value="<%= loanId %>">
                                <button type="submit" class="approve_btn">Approve</button>
                            </form>

                            <form action="reject_loan.jsp" method="post" style="display:inline;">
                                <input type="hidden" name="loan_id" value="<%= loanId %>">
                                <button type="submit" class="reject_btn">Reject</button>
                            </form>
                        <% } else if(statusClass.equals("approved")) { %>
                            <span class="approved_text">Approved</span>
                        <% } else { %>
                            <span class="rejected_text">Rejected</span>
                        <% } %>
                    </td>
                </tr>

<%
        }

        if(!hasData){
%>
            <tr>
                <td colspan="9" class="no_data">No applications found.</td>
            </tr>
<%
        }
%>
            </tbody>
        </table>
    </div>

<%
    }
} catch(Exception e){
    out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
}
%>

</section>
<footer class="admin_footer">
    © 2026 RupeeLoans. All rights reserved.
</footer>
</body>
</html>
