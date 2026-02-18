<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, util.DBConnection" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>User Dashboard - RupeeLoans</title>
    <link rel="stylesheet" href="common.css">
    <link rel="stylesheet" href="about_style.css">
    <link rel="stylesheet" href="home_style.css">
    <link rel="stylesheet" href="dashboard_style.css">

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
    />
</head>

<body>

<!-- ================= HEADER ================= -->
<header class="website_header">
    <div class="company_logo">Rupee<span>Loans</span></div>

    <nav class="main_navigation">
        <a href="home_index.html"><i class="fa-solid fa-house"></i> Home</a>
        <a href="applyloan_index.html"><i class="fa-brands fa-wpforms"></i> Apply For Loan</a>
         <a href="Calculator_index.html"> <i class="fa-solid fa-coins"></i>EMI Calculator</i>
        <a href="about_index.html"><i class="fa-solid fa-circle-info"></i> About</a>
        <a href="contact_index.html"><i class="fa-solid fa-envelope"></i> Contact</a>
        <a href="logout.html"><i class="fa-solid fa-right-from-bracket"></i> Log Out</a>

    </nav>
</header>

<%
    String fullName = (String) session.getAttribute("full_name");
    Integer userId = (Integer) session.getAttribute("user_id");

    if (fullName == null || userId == null) {
        response.sendRedirect("login_index.html");
        return;
    }

    // Fetch user's loans
    ResultSet loansRs = null;
    try {
        Connection conn = DBConnection.getConnection();
        String loanQuery = "SELECT loan_id, applied_at, loan_status, loan_amount FROM loan_applications WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(loanQuery);
        ps.setInt(1, userId);
        loansRs = ps.executeQuery();
%>

<!-- ================= DASHBOARD ================= -->
<section class="dashboard_section">

    <div class="dashboard_header">
        <h2>Hello, <span class="user_name"><%= fullName %></span> 👋</h2>
        <p>Welcome back to your loan dashboard</p>
    </div>

    <div class="profile_card">
        <div class="profile_icon">
            <i class="fa-solid fa-circle-user"></i>
        </div>

        <div class="profile_details">
            <p><i class="fa-solid fa-user"></i> <strong>Name:</strong> <%= fullName %></p>
            <p><i class="fa-solid fa-id-card"></i> <strong>User ID:</strong> <%= userId %></p>
        </div>
    </div>

    <div class="loan_section">
        <h3><i class="fa-solid fa-file-contract"></i> Applied Loans</h3>

        <table class="loan_table">
            <thead>
                <tr>
                    <th>Loan ID</th>
                    <th>Applied On</th>
                    <th>Amount</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    boolean hasLoans = false;
                    while (loansRs.next()) {
                        hasLoans = true;
                        int loanId = loansRs.getInt("loan_id");
                        Timestamp appliedAt = loansRs.getTimestamp("applied_at");
                        String loanStatus = loansRs.getString("loan_status");
                        double loanAmount = loansRs.getDouble("loan_amount");
                %>
                <tr>
                    <td><%= loanId %></td>
                    <td><%= appliedAt %></td>
                    <td>₹<%= String.format("%.2f", loanAmount) %></td>
                    <td class="status <%= loanStatus.toLowerCase() %>"><%= loanStatus %></td>
                </tr>
                <%
                    }
                    if (!hasLoans) {
                %>
                <tr>
                    <td colspan="4" style="text-align:center;">No loans applied yet</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

</section>

<%
    } catch (Exception e) {
        e.printStackTrace();
%>
<div style="color:red; text-align:center;">
    Error loading loans: <%= e.getMessage() %>
</div>
<%
    } finally {
        if (loansRs != null) loansRs.getStatement().getConnection().close();
    }
%>

<!-- ================= FOOTER ================= -->
<footer class="website_footer">
    <p>&copy; 2026 RupeeLoans. All rights reserved.</p>
</footer>

</body>
</html>
