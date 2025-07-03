<%@ page import="java.util.*"%>
<%@ page import="cn.techtutorial.dao.ProductDao"%>
<%@ page import="cn.techtutorial.connection.DbCon"%>
<%@ page import="cn.techtutorial.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
    request.setAttribute("cart_list", cart_list);
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Shopping</title>
<%@include file="includes/head.jsp"%>
<style>
    .card-img-top {
        height: 200px;
        object-fit: cover;
    }
    .card {
        border-radius: 15px;
    }
    .card:hover {
    transform: translateY(-5px);
    transition: 0.3s ease;
    box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);
}
    .card-title {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
    <div class="mt-3 d-flex justify-content-between flex-wrap gap-2">
    
</style>

</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Products</div>
	<div class="row g-4">
<%
if (!products.isEmpty()) {
    for (Product p : products) {
%>
    <div class="col-sm-6 col-md-4 col-lg-3">
        <div class="card h-100 shadow-sm">
            <img class="card-img-top" src="product-images/<%= p.getImage() %>" alt="Card image cap">
            <div class="card-body d-flex flex-column justify-content-between">
                <div>
                    <h5 class="card-title"><%= p.getName() %></h5>
                    <h6 class="price">Price: $<%= p.getPrice() %></h6>
                    <h6 class="category">Category: <%= p.getCategory() %></h6>
                </div>
                <div class="mt-3 d-flex justify-content-between">
                    <a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-dark">Add to Cart</a>
                    <a href="order-now?quantity=1&id=<%= p.getId() %>" class="btn btn-primary">Buy Now</a>
                    <a href="generate-checksum?orderId=<%= p.getId() %>" class="btn btn-success">Pay with Paytm</a>
                </div>
            </div>
        </div>
    </div>
<%
    }
}
%>
</div>


	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>