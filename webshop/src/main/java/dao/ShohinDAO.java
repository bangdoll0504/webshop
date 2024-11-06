package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Shohin;

public class ShohinDAO extends DAOParam {
	//商品名で検索
	public List<Shohin> findByName(String name) throws SQLException {
		System.out.println(JDBC_URL);
		/* List<Shohin> shohinList = new ArrayList<>();
				try (Connection conn = DriverManager.getConnection(
						JDBC_URL, DB_USER, DB_PASS)) { */
        try {
            // JDBCドライバをロード
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBCドライバのロードに失敗しました");
        }

		List<Shohin> shohinList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/webap2023", "root", "password")) {
			String sql = "SELECT Code,Name,Vol,Price,Comment,Image " +
					"FROM Shohin WHERE Name LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + name + "%");
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				shohinList.add(new Shohin(
						rs.getString("Code"),
						rs.getString("Name"),
						rs.getString("Vol"),
						rs.getInt("Price"),
						rs.getString("Comment"),
						rs.getString("Image")));
			}
		} catch (SQLException e) {
			throw e;
		}
		return shohinList;
	}

	//価格で検索
	public List<Shohin> findByPrice(int low, int high) throws SQLException {
		List<Shohin> shohinList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT Code,Name,Vol,Price,Comment,Image " +
					"FROM Shohin WHERE Price BETWEEN ? AND ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, low);
			pStmt.setInt(2, high);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				shohinList.add(new Shohin(
						rs.getString("Code"),
						rs.getString("Name"),
						rs.getString("Vol"),
						rs.getInt("Price"),
						rs.getString("Comment"),
						rs.getString("Image")));
			}
		} catch (SQLException e) {
			throw e;
		}
		return shohinList;
	}
}