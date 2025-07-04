package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import cn.techtutorial.model.*;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
		super();
		this.con = con;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		try {
			query="select* from products";
            pst = this.con.prepareStatement(query);
            rs=pst.executeQuery();
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
            	Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
                
                products.add(row);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if (cartList.size() > 0) {
			    for (Cart item : cartList) {
			        query = "select * from products where id=?";
			        pst = this.con.prepareStatement(query);
			        pst.setInt(1, item.getId());
			        rs = pst.executeQuery();
			        
			        while (rs.next()) {
			            Cart row = new Cart();
			            row.setId(rs.getInt("id"));
			            row.setName(rs.getString("name"));
			            row.setCategory(rs.getString("category"));
			            row.setPrice(rs.getDouble("price") * item.getQuantity()); 
			            row.setQuantity(item.getQuantity());

			            products.add(row);  
			        }
			    }
			}
		}catch(Exception e) {
//			e.printStackTrace();
		}
		return products;
	}
	
	public Product getSingleProduct(int id) {
		Product row = null;
		try {
			query = "select * from products where id= ?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs =pst.executeQuery();

			while(rs.next()) {
			    row = new Product(); // Create new Product object for each row
			    row.setId(rs.getInt("id"));                // Fetch product ID
			    row.setName(rs.getString("name"));         // Fetch product name
			    row.setCategory(rs.getString("category")); // Fetch product category
			    row.setPrice(rs.getDouble("price"));       // Fetch product price
			    row.setImage(rs.getString("image"));       // Fetch image path or URL
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public Double getTotalCartPrice(ArrayList<Cart> cartList){
		double sum = 0;
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query = "select price from products where id=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1,item.getId());
					rs = pst.executeQuery();
					
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
		
	}
	
	
	
	
}