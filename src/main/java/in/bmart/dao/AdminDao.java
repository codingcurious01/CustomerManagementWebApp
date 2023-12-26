package in.bmart.dao;

import java.sql.*;

public class AdminDao 
{
	private static Connection cn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private static final String validateQry = "select * from admin where admin_username = ? and admin_password = ?";
	
	public static boolean validateAdminDao(String username, String password) 
	{
		boolean flag = false;
		try 
		{
			cn = MyDbConnection.myConnection();

			ps = cn.prepareStatement(validateQry);
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			flag = rs.next();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			if(rs != null) 
			{
				try {
					rs.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) 
			{
				try {
					ps.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(cn != null) 
			{
				try {
					cn.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
}
