package cn.techtutorial.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.OrderDao;
import cn.techtutorial.model.Cart;
import cn.techtutorial.model.Order;
import cn.techtutorial.model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date= new Date();
			
			//retrive all cart products
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			//user authentication
			User auth = (User) request.getSession().getAttribute("auth");
			//check auth andcart list
			if(cart_list!= null && auth!=null) {
				//prepare order object
				for(Cart c: cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(c.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					//instantiate the dao class
					OrderDao oDao = new OrderDao(DbCon.getConnection());
					//calling insert method
					boolean result = oDao.insertOrder(order);
					if(!result) break;
				}
				
				cart_list.clear();
				response.sendRedirect("orders.jsp");
				
			}else {
				if(auth != null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}

			out.println("check out servlet");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
