<%@page import="cn.techtutorial.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.techtutorial.model.Cart"%>

<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
  <div class="container">
    <!-- Brand with optional logo -->
    <a class="navbar-brand d-flex align-items-center" href="index.jsp">
      <strong="logo-font">FlexFeet</strong>
    </a>

    <!-- Toggler -->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
      data-bs-target="#navbarNav" aria-controls="navbarNav"
      aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Navbar links -->
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto align-items-center">
        <li class="nav-item">
          <a class="nav-link" href="index.jsp">Home</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="cart.jsp">
            Cart 
            <span class="badge bg-danger">
              <%= (session.getAttribute("cart-list") != null) ? ((ArrayList)session.getAttribute("cart-list")).size() : 0 %>
            </span>
          </a>
        </li>

        <% if (request.getAttribute("auth") != null) { %>
        <li class="nav-item">
          <a class="nav-link" href="orders.jsp">Orders</a>
        </li>
        <li class="nav-item">
          <a class="btn btn-outline-danger ms-2" href="log-out">Logout</a>
        </li>
        <% } else { %>
        <li class="nav-item">
          <a class="btn btn-outline-primary ms-2" href="login.jsp">Login</a>
        </li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>
