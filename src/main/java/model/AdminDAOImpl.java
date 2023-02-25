package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {
	public static Connection con;

	public static void initDataBase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver is loaded.");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airways", "root", "Nilesh@1991");
			System.out.println("Connection is established.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean Adminsignin(Admin admin) {
		try {
			PreparedStatement pst = con.prepareStatement("select * from admin where username=? and password=?");
			pst.setString(1, admin.getUsername());
			pst.setString(2, admin.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs != null && rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean Adminsignup(Admin admin) {

		int res = -1;
		try {
			PreparedStatement pst = con.prepareStatement("insert into admin (username, password) values (?,?)");
			pst.setString(1, admin.getUsername());
			pst.setString(2, admin.getPassword());
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (res == 1) ? true : false;
	}

}
