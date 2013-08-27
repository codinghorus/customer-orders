package com.sample.jmmbtest.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sample.jmmbtest.domain.Order;
import com.sample.jmmbtest.domain.Product;

public class OrdersDao {

	public Order create(Order order) throws Exception {
		Connection conn = null;
		String sql = "INSERT INTO Orders (ProductId, CustomerId) VALUES (?, ?)";
			
		try {
			conn = Database.getConnection();
			String orderKey = "OrderId";
			PreparedStatement statement = conn.prepareStatement(sql, new String[] { orderKey });
			statement.setInt(1, order.getProductId());
			statement.setInt(2, order.getCustomerId());
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();			
			rs.next();
			int OrderId = rs.getInt(1);
			order.setOrderId(OrderId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.close(conn);			
		}
		
//		ProductDao productDao = new ProductDao();
//		Product product = productDao.findProductById(order.getProductId());
		// TODO: Call Customer Inventory Management System service API with the specific product ordered.
		
		return order;
	}
}
