package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UserCRUD extends CRUD{

	private String statement;
	private PreparedStatement query;
	
	@Override
	public void setState(String statement) {
		this.statement = statement;
	}
	
	private static Connection dbconn = DBConnector.connectDB();

	public void create(User user) {
		if(dbconn != null) {
			try {
				query = (PreparedStatement) dbconn.prepareStatement(statement);
				query.setString(1, user.getFname());
				query.setString(2, user.getLname());
				query.setString(3, user.getAddress());
				query.setString(4, user.getUser());
				query.setString(5, user.getPass());
				query.executeUpdate();
				JOptionPane.showMessageDialog(null, "User data inserted succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection not available");
		}

	}
}
