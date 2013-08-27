package com.sample.jmmbtest.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sample.jmmbtest.domain.Product;

public class ProductDao {

	public List<Product> findProductsMatching(String criteria) throws SQLException {
		List<Product> result = new ArrayList<Product>();
		Connection conn = null;
		String sql = "SELECT ProductId, ProductName, Importance FROM Products WHERE ProductName LIKE ? "
				+ "ORDER BY ProductName";
		if (criteria == null) criteria = "";
		
		try {
			conn = Database.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + criteria + "%");
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
				result.add(getProductFrom(rs));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.close(conn);			
		}		
		
		return result;
	}
	
	public Product findProductById(int productId) {
		Product product = null;
		Connection conn = null;
		String sql = "SELECT ProductId, ProductName, Importance FROM Products WHERE ProductId = ?";

		try {
			conn = Database.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, productId);
			ResultSet rs = statement.executeQuery();

			if (rs.next())
				product = getProductFrom(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.close(conn);
		}
		
		return product;
	}
	
	public Product create(Product product) throws Exception {
		Connection conn = null;
		String sql = "INSERT INTO Products (ProductName, Importance) VALUES (?, ?)";
			
		try {
			conn = Database.getConnection();
			String productKey = "ProductId";
			PreparedStatement statement = conn.prepareStatement(sql, new String[] { productKey });
			statement.setString(1, product.getProductName());
			statement.setInt(2, product.getImportance());
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();			
			rs.next();
			int productId = rs.getInt(1);
			product.setProductId(productId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.close(conn);			
		}
		return product;
	}
	
	private Product getProductFrom(ResultSet rs) throws SQLException {
		Product product = new Product(rs.getString("ProductName"), rs.getInt("Importance"));
		product.setProductId(rs.getInt("ProductId"));
		return product;
	}
}
