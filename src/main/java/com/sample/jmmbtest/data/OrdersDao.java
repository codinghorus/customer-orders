package com.sample.jmmbtest.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sample.jmmbtest.domain.Order;
import com.sample.jmmbtest.domain.OrderedProduct;
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
	
	public List<OrderedProduct> orderedProducts() {
		Connection conn = null;
		List<OrderedProduct> result = new ArrayList<OrderedProduct>(); 
		
		String sql = "SELECT o.OrderId, o.ProductId, o.CustomerId, p.ProductName, p.Importance, c.Priority, "
				+ "c.Address FROM Orders as o "
				+ "JOIN Products p ON o.ProductId = p.ProductId "
				+ "JOIN Customers c on o.CustomerId = c.CustomerId "
				+ "ORDER BY c.Priority DESC, p.Importance DESC";
			
		try {
			conn = Database.getConnection();
			String orderKey = "OrderId";
			PreparedStatement statement = conn.prepareStatement(sql);

			
			ResultSet rs = statement.executeQuery();			
			while (rs.next()) {
				result.add(getOrderedProductFrom(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.close(conn);			
		}
		
		return result;
	}

	private OrderedProduct getOrderedProductFrom(ResultSet rs) throws SQLException {
		OrderedProduct orderedProduct = new OrderedProduct();
		
		try {
			orderedProduct.OrderId = rs.getInt("OrderId");
			orderedProduct.ProductId = rs.getInt("ProductId");
			orderedProduct.ProductName = rs.getString("ProductName");
			orderedProduct.ProductImportance = rs.getInt("Importance");
			orderedProduct.CustomerId = rs.getInt("CustomerId");
			orderedProduct.CustomerPriority = rs.getInt("Priority");
			orderedProduct.CustomerAddress = rs.getString("Address");
		} catch (SQLException e) {
			throw e;
		}

		return orderedProduct;
	}
}
