package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


// Inheritance 
public class MovieCRUD extends CRUD {
	
	// Encapsulation
	private String statement;
	private PreparedStatement query;
	
	// Object named dbconn
	private static Connection dbconn = DBConnector.connectDB();

	@Override
	public void setState(String statement) {
		this.statement = statement;
	}

	public boolean create(Movie movie) {
		if(dbconn != null) {
			try {
				query = (PreparedStatement) dbconn.prepareStatement(statement);
				query.setString(1, movie.getTitle());
				query.setString(2, movie.getGenre());
				query.setString(3, movie.getDuration());
				query.setString(4, movie.getPublishedDate());
				int rowsAffected = query.executeUpdate();
			    return rowsAffected > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection not available");
		}
		return false;
	}
	
	public boolean readAll(DefaultTableModel model, String statement) {
		if(dbconn != null) {
			try {
				query = (PreparedStatement) dbconn.prepareStatement(statement);
				ResultSet rs = query.executeQuery();
				while (rs.next()) {
			        String Title = rs.getString("title");
			        String Genre = rs.getString("genre");
			        String Duration = rs.getString("duration");
			        String PublishedDate = rs.getString("publisheddate");
			        Object[] row = {Title, Genre, Duration, PublishedDate};
			        model.addRow(row);
			      }
			      return true; // Indicate successful data retrieval

				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean update(int confirmation, String currentMovie, Movie movie) {
		if(dbconn != null) {
			try {
				query = (PreparedStatement) dbconn.prepareStatement(statement);
			    if (confirmation == JOptionPane.YES_OPTION) {
				    	query.setString(1, movie.getTitle());
				       	query.setString(2, movie.getGenre());
				       	query.setString(3, movie.getDuration());
				       	query.setString(4, movie.getPublishedDate());
				       	query.setString(5, currentMovie); 
				       	int rowsUpdated = query.executeUpdate();
				        return rowsUpdated > 0;
			    } else {
			        return false;
			    }
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean delete(Movie movie) {
		if(dbconn != null) {
			try {
				query = (PreparedStatement) dbconn.prepareStatement(statement);
				query.setString(1, movie.getTitle()); 
				int rowsDeleted = query.executeUpdate();
				return rowsDeleted > 0;
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return false;
	}

	
}
