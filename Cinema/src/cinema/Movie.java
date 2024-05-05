package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Movie {

    private String title;
    private String genre;
    private String duration;
    private String publishedDate;
    private int id;
    private static Connection dbconn = DBConnector.connectDB();

    public Movie(String title, String genre, String duration, String publishedDate) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.publishedDate = publishedDate;
    }
    
    public Movie(String title, String genre, String duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public void setTitle (String title) {
    	this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public void setGenre (String genre) {
    	this.genre = genre;
    }
    
    public String getGenre() {
    	return genre;
    }
    
    public void setDuration (String duration) {
    	this.duration = duration;
    }
    
    public String getDuration() {
    	return duration;
    }
    
    public void setPublishedDate (String publishedDate) {
    	this.publishedDate = publishedDate;
    }
    
    public String getPublishedDate() {
    	return publishedDate;
    }
    
    public int getId() {
		 if(dbconn != null) {
	    		try {
					PreparedStatement st = (PreparedStatement) dbconn.prepareStatement("SELECT id FROM movies WHERE title = ? AND genre = ? AND duration = ?");
					st.setString(1, this.title);
					st.setString(2, this.genre);
					st.setString(3, this.duration);
					ResultSet rs = st.executeQuery();
					if(rs.next()) {
						this.id = rs.getInt("id");					
						return id;
					} else {
						return 0;
					}
	    		} catch (SQLException e) {
					e.printStackTrace();
				}
	    	} else {
				System.out.println("Connection not available");
			}
	    	return 0;
	 }
}
