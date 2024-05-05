package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class TicketCRUD extends CRUD {
	
	private String statement;
	private PreparedStatement query;
	private static Connection dbconn = DBConnector.connectDB();
	
	@Override
	public void setState(String statement) {
		this.statement = statement;
	}
	
	public void create (Ticket ticket) {
		if (dbconn != null) {
			try {
				query = (PreparedStatement) dbconn.prepareStatement(statement);
				query.setInt(1, ticket.getUser());
				query.setInt(2, ticket.getMovie());
				query.setInt(3, ticket.getAmount());
				query.setInt(4, ticket.getPaid());
				query.executeUpdate();
				if(ticket.getPaid() == 0) {
					JOptionPane.showMessageDialog(null, "Ticket data booked succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				if (ticket.getPaid() == 1) {
					JOptionPane.showMessageDialog(null, "Ticket data buyed succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection not available");
		}
	}
	
	public void updateAll(User user) {
		if (dbconn != null) {
			try {
				query = (PreparedStatement) dbconn.prepareStatement(statement);
				query.setInt(1, 1);
				query.setInt(2, user.getId());
				query.executeUpdate();
				JOptionPane.showMessageDialog(null, "All tickets paid succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection not available");
		}
	}
	
	public void deleteAll(User user) {
		if (dbconn != null) {
			try {
				query = (PreparedStatement) dbconn.prepareStatement(statement);
				query.setInt(1, user.getId());
				query.executeUpdate();
				JOptionPane.showMessageDialog(null, "All tickets canceled succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection not available");
		}
	}
	
	public int countUnpaidTickets(User user) {
		if(dbconn != null) {
			try {
				query = (PreparedStatement) dbconn.prepareStatement("SELECT * FROM Tickets WHERE userid = ? AND paid = ?");
				query.setInt(1, user.getId());
				query.setInt(2, 0);
				ResultSet rs = query.executeQuery();
				int tickets = 0;
				while (rs.next()) {
					tickets += rs.getInt("amount");
			      }
			     return tickets; 

				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int countPaidTickets(User user) {
		if(dbconn != null) {
			try {
				query = (PreparedStatement) dbconn.prepareStatement("SELECT * FROM Tickets WHERE userid = ? AND paid = ?");
				query.setInt(1, user.getId());
				query.setInt(2, 1);
				ResultSet rs = query.executeQuery();
				int tickets = 0;
				while (rs.next()) {
			        tickets += rs.getInt("amount");
			      }
			     return tickets; 

				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
