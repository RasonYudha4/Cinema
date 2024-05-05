package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User {
	private int id;
	private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String address;
    
    private static Connection dbconn = DBConnector.connectDB();

    public User(String firstname, String lastname, String address, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.username = username;
        this.password = password;
    }
    
    public User(String username, String password) {
    	this.username = username;
    	this.password = password;
    }

    public void setFname (String firstname) {
    	this.firstname = firstname;
    }
    
    public String getLname() {
    	if(dbconn != null) {
    		try {
				PreparedStatement st = (PreparedStatement) dbconn.prepareStatement("SELECT lastname FROM Users WHERE username = ? AND password = ?");
				st.setString(1, this.username);
				st.setString(2, this.password);
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					this.lastname = rs.getString("lastname");					
					return lastname;
				} else {
					return lastname;
				}
    		} catch (SQLException e) {
				e.printStackTrace();
			}
    	} else {
			System.out.println("Connection not available");
		}
    	return lastname;
    }

    public void setLname (String lastname) {
    	this.lastname = lastname;
    }
    
    public String getFname() {
    	if(dbconn != null) {
    		try {
				PreparedStatement st = (PreparedStatement) dbconn.prepareStatement("SELECT firstname FROM Users WHERE username = ? AND password = ?");
				st.setString(1, this.username);
				st.setString(2, this.password);
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					this.firstname = rs.getString("firstname");					
					return firstname;
				} else {
					return firstname;
				}
    		} catch (SQLException e) {
				e.printStackTrace();
			}
    	} else {
			System.out.println("Connection not available");
		}
    	return firstname;
    }
    
    public void setAddress (String address) {
    	this.address = address;
    }

    public String getAddress() {
    	if(dbconn != null) {
    		try {
				PreparedStatement st = (PreparedStatement) dbconn.prepareStatement("SELECT address FROM Users WHERE username = ? AND password = ?");
				st.setString(1, this.username);
				st.setString(2, this.password);
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					this.address = rs.getString("address");					
					return address;
				} else {
					return address;
				}
    		} catch (SQLException e) {
				e.printStackTrace();
			}
    	} else {
			System.out.println("Connection not available");
		}
    	return address;
    }

    
    public void setUser (String username) {
    	this.username = username;
    }
    
    public String getUser() {
    	if(dbconn != null) {
    		try {
				PreparedStatement st = (PreparedStatement) dbconn.prepareStatement("SELECT username FROM Users WHERE username = ? AND password = ?");
				st.setString(1, this.username);
				st.setString(2, this.password);
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					this.username = rs.getString("username");					
					return username;
				} else {
					return username;
				}
    		} catch (SQLException e) {
				e.printStackTrace();
			}
    	} else {
			System.out.println("Connection not available");
		}
    	return username;
    }
    
    public void setPass (String password) {
    	this.password = password;
    }
    
    public String getPass() {
    	if(dbconn != null) {
    		try {
				PreparedStatement st = (PreparedStatement) dbconn.prepareStatement("SELECT password FROM Users WHERE username = ? AND password = ?");
				st.setString(1, this.username);
				st.setString(2, this.password);
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					this.password = rs.getString("password");					
					return password;
				} else {
					return password;
				}
    		} catch (SQLException e) {
				e.printStackTrace();
			}
    	} else {
			System.out.println("Connection not available");
		}
    	return password;
    }
    
    public int getId() {
    	if(dbconn != null) {
    		try {
				PreparedStatement st = (PreparedStatement) dbconn.prepareStatement("SELECT id FROM Users WHERE username = ? AND password = ?");
				st.setString(1, this.username);
				st.setString(2, this.password);
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
    
    public void setId(int id) {
    	this.id = id;
    }
}
