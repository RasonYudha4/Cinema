package cinema;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddMovie extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField PublishedDate;
	private JTextField Duration;
	private JTextField Genre;
	private JTextField Title;
	private JTable tbData;
	public static String username;
	
	//Object
	MovieCRUD crud = new MovieCRUD();
	Movie movie;

	DefaultTableModel model = new DefaultTableModel(
            new Object[][] {},
            new String[] {"Movie Title", "Genre", "Duration", "Published Date"}
    );
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddMovie(User user) {
		User loginUser = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1208, 607);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Welcome To");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 30));
		
		JLabel lblTicketManagingSystem = new JLabel("Ticket Managing System");
		lblTicketManagingSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTicketManagingSystem.setForeground(Color.WHITE);
		lblTicketManagingSystem.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 18));
		
		JPanel LeftPanel = new JPanel();
		LeftPanel.setBackground(new Color(255, 255, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 64, 128));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(LeftPanel, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(386)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(362)
							.addComponent(lblTicketManagingSystem, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 962, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTicketManagingSystem, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(228, Short.MAX_VALUE))
				.addComponent(LeftPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
		);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(223, 223, 223));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(223, 223, 223));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 401, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(69)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tbData = new JTable();

		boolean success = crud.readAll(model, "SELECT title, genre, duration, publishedDate FROM Movies");
		if (success) {
			tbData.setModel(model);
		} else {
			JOptionPane.showMessageDialog(null, "No Data Available", "Error", JOptionPane.ERROR_MESSAGE);
		}
		tbData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tbData.getSelectedRow();
				Title.setText(model.getValueAt(i, 0).toString());
				Genre.setText(model.getValueAt(i, 1).toString());
				Duration.setText(model.getValueAt(i, 2).toString());
				PublishedDate.setText(model.getValueAt(i, 3).toString());
			}
		});
		scrollPane.setViewportView(tbData);
		
		tbData.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 64, 128), null, null, null));
		tbData.setColumnSelectionAllowed(true);
		tbData.setCellSelectionEnabled(true);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Title:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_2_1 = new JLabel("Genre:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Duration:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Published Date:");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnNewButton_3 = new JButton("Insert");
		btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Title.getText().isEmpty() || Genre.getText().isEmpty() || Duration.getText().isEmpty() || PublishedDate.getText().isEmpty()) {
                	JOptionPane.showMessageDialog(null, "Please Fill all the required form", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	String 	title = Title.getText(), 
                			genre = Genre.getText(), 
                			duration = Duration.getText(), 
                			publishedDate = PublishedDate.getText();
                	
                	crud.setState("INSERT INTO movies (`title`, `genre`, `duration`, `publisheddate`) VALUES (?, ?, ?, ?)");
                	movie = new Movie(title, genre, duration, publishedDate);
                	boolean success = crud.create(movie); 

                	  if (success) {
                	    Object[] row = {title, genre, duration, publishedDate};
                	    model.addRow(row);
                	    JOptionPane.showMessageDialog(null, "Movie data inserted succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                	  } else {
                	    JOptionPane.showMessageDialog(null, "Failed to insert movie data", "Error", JOptionPane.ERROR_MESSAGE);
                	  }
                    Title.setText("");
                    Genre.setText("");
                    Duration.setText("");
                    PublishedDate.setText("");
                }
                
            }
        });
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(0, 0, 255));
		
		JButton btnNewButton_3_1 = new JButton("Delete");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crud.setState("DELETE FROM movies WHERE title = ?");
				int selectedRow = tbData.getSelectedRow();
				if (selectedRow >= 0 && JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this movie?", "Delete Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String 	title = (String) model.getValueAt(selectedRow, 0),
							genre = Genre.getText(), 
                			duration = Duration.getText(), 
                			publishedDate = PublishedDate.getText();
					
					movie = new Movie(title, genre, duration, publishedDate);
					
					boolean success = crud.delete(movie);
					if (success) {
					    model.removeRow(selectedRow); 
					    JOptionPane.showMessageDialog(null, "Movie data deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					} else {
					    JOptionPane.showMessageDialog(null, "Failed to delete movie data", "Error", JOptionPane.ERROR_MESSAGE);
					}
					Title.setText("");
                    Genre.setText("");
                    Duration.setText("");
                    PublishedDate.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row first", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_3_1.setForeground(new Color(255, 255, 255));
		btnNewButton_3_1.setBackground(new Color(128, 0, 64));
		
		JButton btnNewButton_3_2 = new JButton("Update");
		btnNewButton_3_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_3_2.setBackground(new Color(0, 128, 128));
		btnNewButton_3_2.setForeground(new Color(255, 255, 255));
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crud.setState("UPDATE movies SET `title`= ?, `genre`= ?,`duration`= ?,`publisheddate`= ? WHERE title = ?");
				int selectedRow = tbData.getSelectedRow();
				if (selectedRow >= 0) {
					String 	title = (String) model.getValueAt(selectedRow, 0),
							newTitle = Title.getText(),
							newGenre = Genre.getText(),
							newDuration = Duration.getText(),
							newPublishedDate = PublishedDate.getText();
					
					int confirmation = JOptionPane.showConfirmDialog(null,
				    		"Current values:\nTitle: " + model.getValueAt(selectedRow, 0) + "\nGenre: " + model.getValueAt(selectedRow, 1) + "\nDuration: " + model.getValueAt(selectedRow, 2) + "\nPublished Date: " + model.getValueAt(selectedRow, 3) +
				    		"\n\nUpdate to:\nTitle: " + newTitle + "\nGenre: " + newGenre + "\nDuration: " + newDuration + "\nPublished Date: " + newPublishedDate + "\n\nAre you sure?",
				            "Update Confirmation", JOptionPane.YES_NO_OPTION);
					
					movie = new Movie(title, newGenre, newDuration, newPublishedDate);
					
					movie.setTitle(newTitle);
					movie.setGenre(newGenre);
					movie.setDuration(newDuration);
					movie.setPublishedDate(newPublishedDate);
					
					boolean success = crud.update(confirmation, title, movie);
					if (success) {
						model.setValueAt(newTitle, selectedRow, 0); 
		        	    model.setValueAt(newGenre, selectedRow, 1);  
		        	    model.setValueAt(newDuration, selectedRow, 2);
		        	    model.setValueAt(newPublishedDate, selectedRow, 3);
		
		        	    JOptionPane.showMessageDialog(null, "Movie data updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					} else {
		        	    JOptionPane.showMessageDialog(null, "Failed to update movie data", "Error", JOptionPane.ERROR_MESSAGE);
		        	}
					Title.setText("");
                    Genre.setText("");
                    Duration.setText("");
                    PublishedDate.setText("");
				} else {
				    JOptionPane.showMessageDialog(null, "Please select a row first", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		PublishedDate = new JTextField();
		PublishedDate.setColumns(10);
		
		Duration = new JTextField();
		Duration.setColumns(10);
		
		Genre = new JTextField();
		Genre.setColumns(10);
		
		Title = new JTextField();
		Title.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(8)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(btnNewButton_3_2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(btnNewButton_3_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2_1_1_1)
								.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(Title, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addComponent(Genre, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addComponent(Duration, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addComponent(PublishedDate, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))))
					.addGap(27))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(152)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(Title, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(Genre, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(Duration, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(PublishedDate, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
					.addGap(46)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_3_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addGap(25))
		);
		panel_2.setLayout(gl_panel_2);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome, ");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel LoggedUsername = new JLabel(loginUser.getUser());
		LoggedUsername.setForeground(new Color(0, 0, 0));
		LoggedUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnNewButton = new JButton("Dashboard");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard d = new Dashboard(loginUser);
				d.setTitle("Dashboard");
				d.setLocationRelativeTo(null);
				d.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 255));
		
		JButton btnNewButton_1 = new JButton("Add Movies");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 128, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnNewButton_2 = new JButton("Buy Ticket");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BuyTicket bt = new BuyTicket(loginUser);
				bt.setLocationRelativeTo(null);
				bt.setVisible(true);
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(0, 128, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login l = new Login();
				l.setLocationRelativeTo(null);
				l.setVisible(true);
			}
		});
		btnSignOut.setForeground(new Color(255, 255, 128));
		btnSignOut.setBackground(new Color(1, 5, 175));
		btnSignOut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_LeftPanel = new GroupLayout(LeftPanel);
		gl_LeftPanel.setHorizontalGroup(
			gl_LeftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LeftPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LeftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
						.addComponent(LoggedUsername)
						.addComponent(lblNewLabel_1)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
						.addComponent(btnSignOut, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_LeftPanel.setVerticalGroup(
			gl_LeftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LeftPanel.createSequentialGroup()
					.addGap(120)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(LoggedUsername, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(101)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
					.addGap(90)
					.addComponent(btnSignOut, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
					.addContainerGap())
		);
		LeftPanel.setLayout(gl_LeftPanel);
		contentPane.setLayout(gl_contentPane);
	}
}
