package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import cn.techtutorial.model.*;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDao(Connection con) {
		this.con = con;
	}
	
	public boolean insertOrder(Order model) {
		boolean result = false;
		try {
			query = "insert into orders(p_id, u_id, o_quantity,o_date) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, model.getId());         // Set first ? to model's ID (int)
			pst.setInt(2, model.getUid());        // Set second ? to user ID (int)
			pst.setInt(3, model.getQuantity());   // Set third ? to quantity (int)
			pst.setString(4, model.getDate());    // Set fourth ? to date (String)
			pst.executeUpdate();                  // Executes the update query
			result = true;                        // Sets result to true if no exception

		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Order> userOrders(int id){
		List<Order> list = new ArrayList<>();
		try {
			query = "select * from orders where u_id=? order by orders.o_id desc";
			pst = this.con.prepareStatement(query);  // Prepare SQL statement
			pst.setInt(1, id);                        // Set user ID in query
			rs = pst.executeQuery();                 // Execute query
			
			while(rs.next()) {
			    Order order = new Order();                          // New Order object
			    ProductDao productDao = new ProductDao(this.con);   // Product DAO with DB connection
			    int pId = rs.getInt("p_id");                        // Get product ID from result
			    	
			    Product product = productDao.getSingleProduct(pId); // Fetch Product by ID
			    order.setOrderId(rs.getInt("o_id"));                // Set order ID
			    order.setId(pId);                                   // Set product ID
			    order.setName(product.getName());                   // Set product name from Product object
			    order.setCategory(product.getCategory());           // Set category from Product object
			    order.setPrice(product.getPrice() * rs.getInt("o_quantity")); // Calculate total price
			    order.setQuantity(rs.getInt("o_quantity"));         // Set quantity
			    order.setDate(rs.getString("o_date"));              // Set order date
			    list.add(order);                                    // Add order to list
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void cancelOrder(int id) {
		try {
			query = "delete from orders where o_id =?";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,id);
			pst.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
