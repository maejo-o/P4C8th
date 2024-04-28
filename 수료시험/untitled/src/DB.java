import java.util.*;
import java.sql.*;

public class DB {
	java.sql.Connection conn;
	java.sql.Statement stmt;
	java.sql.ResultSet rs;
	
	DB() {
		db_info();
	}
	
	void db_info() {
		String db_info = "jdbc:mysql://localhost/twitter";
		String db_id = "root";
		String db_pw = "0000";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = java.sql.DriverManager.getConnection(db_info, db_id, db_pw);
			this.stmt = this.conn.createStatement();
		} catch (Exception e) {
			System.out.println("connection error : " + e);
		}
	}
	
	void select(String sql) {
		try {
			this.rs = this.stmt.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("excuteQuery error : " + e);
		}
	}
	
	void insert(String sql) {
		try {
			this.stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("excuteUpdate error : " + e);
		}
	}
	
	void update(String sql) {
		try {
			this.stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("excuteUpdate error : " + e);
		}
	}
	
	void delete(String sql) {
		try {
			this.stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("excuteUpdate error : " + e);
		}
	}
	
	void close() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println("close error : " + e);
		}
	}
}
	