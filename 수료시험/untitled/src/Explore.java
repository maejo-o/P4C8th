//package tweet;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class Explore {

	JFrame frame;
	private JTextField Search_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Explore window = new Explore();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public Explore() {
		initialize();
	}
	
	String search_str = "";
	public Explore(String str) {
		search_str = str;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DB connect = new DB();
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/image/logo.png")));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setTitle("twitter");
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel logo_panel = new JPanel();
		logo_panel.setBackground(new Color(255, 255, 255));
		logo_panel.setBounds(0, 5, 135, 50);
		frame.getContentPane().add(logo_panel);
		logo_panel.setLayout(null);
		
		JLabel logo_lNewLabel = new JLabel("");
		logo_lNewLabel.setIcon(new ImageIcon(Home.class.getResource("/image/mainlogo.png")));
		logo_lNewLabel.setBounds(0, 0, 135, 50);
		logo_panel.add(logo_lNewLabel);
		
		JPanel manu_panel = new JPanel();
		manu_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		manu_panel.setBackground(new Color(255, 255, 255));
		manu_panel.setBounds(0, 60, 135, 152);
		frame.getContentPane().add(manu_panel);
		manu_panel.setLayout(null);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(255, 255, 255));
		btnHome.setFont(new Font("나눔고딕", Font.BOLD, 10));
		btnHome.setHorizontalAlignment(SwingConstants.LEFT);
		btnHome.setBounds(42, 10, 85, 25);
		btnHome.setBorderPainted(false);
		manu_panel.add(btnHome);
		
		btnHome.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Home new_window = new Home();
				new_window.frame.setVisible(true);
				frame.setVisible(false);
				
			}
		});
		
		JButton btnExplore = new JButton("Explore");
		btnExplore.setBackground(new Color(255, 255, 255));
		btnExplore.setHorizontalAlignment(SwingConstants.LEFT);
		btnExplore.setFont(new Font("나눔고딕", Font.BOLD, 10));
		btnExplore.setBounds(42, 45, 85, 25);
		btnExplore.setBorderPainted(false);
		manu_panel.add(btnExplore);
		btnExplore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Explore new_window = new Explore();
				new_window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.setBackground(new Color(255, 255, 255));
		btnProfile.setHorizontalAlignment(SwingConstants.LEFT);
		btnProfile.setFont(new Font("나눔고딕", Font.BOLD, 10));
		btnProfile.setBounds(42, 80, 85, 25);
		btnProfile.setBorderPainted(false);
		manu_panel.add(btnProfile);
		
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile new_window = new Profile();
				new_window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBackground(new Color(255, 255, 255));
		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogout.setFont(new Font("나눔고딕", Font.BOLD, 10));
		btnLogout.setBounds(42, 115, 85, 25);
		btnLogout.setBorderPainted(false);
		manu_panel.add(btnLogout);

		btnLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Login f0 = new Login();
				frame.setVisible(false);
				
			}
		});
		
		JLabel Home_label = new JLabel("");
		Home_label.setHorizontalAlignment(SwingConstants.LEFT);
		Home_label.setIcon(new ImageIcon(Explore.class.getResource("/image/Home.png")));
		Home_label.setBounds(12, 10, 30, 25);
		manu_panel.add(Home_label);
		
		JLabel Explore_label = new JLabel("");
		Explore_label.setIcon(new ImageIcon(Explore.class.getResource("/image/Search_bold.png")));
		Explore_label.setBounds(12, 45, 30, 25);
		manu_panel.add(Explore_label);
		
		JLabel Profile_label = new JLabel("");
		Profile_label.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
		Profile_label.setBounds(12, 80, 30, 25);
		manu_panel.add(Profile_label);
		
		JLabel Logout_label = new JLabel("");
		Logout_label.setIcon(new ImageIcon(Home.class.getResource("/image/Logout.png")));
		Logout_label.setBounds(12, 115, 30, 25);
		manu_panel.add(Logout_label);
		
		JPanel status_panel = new JPanel();
		status_panel.setToolTipText("");
		status_panel.setBackground(new Color(255, 255, 255));
		status_panel.setBounds(144, 5, 338, 50);
		frame.getContentPane().add(status_panel);
		status_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Explore");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 26));
		lblNewLabel.setBounds(0, 0, 100, 50);
		status_panel.add(lblNewLabel);
		
		JPanel search_panel = new JPanel();
		search_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		search_panel.setBackground(Color.WHITE);
		search_panel.setBounds(144, 60, 338, 77);
		frame.getContentPane().add(search_panel);
		search_panel.setLayout(null);
		
		JLabel Waht_label = new JLabel("Who will you follow?");
		Waht_label.setFont(new Font("나눔고딕", Font.BOLD, 10));
		Waht_label.setBounds(12, 7, 150, 20);
		search_panel.add(Waht_label);
		
		Search_textField = new JTextField();
		Search_textField.setBackground(new Color(211, 211, 211));
		Search_textField.setBounds(12, 29, 280, 25);
		search_panel.add(Search_textField);
		Search_textField.setColumns(10);
		
		JButton Follow_button = new JButton("");
		Follow_button.setIcon(new ImageIcon(Explore.class.getResource("/image/Search_bold.png")));
		Follow_button.setFont(new Font("나눔고딕", Font.BOLD, 10));
		Follow_button.setBounds(296, 29, 30, 25);
		Follow_button.setBorderPainted(false);
		search_panel.add(Follow_button);	
		Follow_button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Explore new_window = new Explore(Search_textField.getText());
				new_window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		
		JPanel User_panel = new JPanel();
		User_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		User_panel.setBackground(Color.WHITE);
		User_panel.setBounds(144, 147, 338, 514);
		frame.getContentPane().add(User_panel);
		User_panel.setLayout(null);
		
		String sql = "";
		if(search_str.equals("")) {
			sql = "SELECT * FROM user LIMIT 0, 0";
		} else {
			sql = "SELECT * FROM user WHERE id LIKE '" + search_str + "%' AND id != '" + User.id + "'";
		}
		System.out.println(sql);
		DB user_connect = new DB();
		user_connect.select(sql);
		
		try {
			if(user_connect.rs.next()) {
				String id = user_connect.rs.getString("id");
				JPanel content_panel = new JPanel();
				content_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
				content_panel.setBackground(new Color(255, 255, 255));
				content_panel.setBounds(12, 10, 314, 42);
				User_panel.add(content_panel);
				content_panel.setLayout(null);
				
				JLabel user_label = new JLabel("");
				user_label.setBounds(12, 5, 30, 25);
				content_panel.add(user_label);
				user_label.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
				
				JLabel id_label = new JLabel(id);
				id_label.setBounds(46, 7, 80, 25);
				content_panel.add(id_label);
				
				JButton plus_button = new JButton("");
				plus_button.setBackground(Color.WHITE);
				plus_button.setIcon(new ImageIcon(Home.class.getResource("/image/Plus.jpg")));
				plus_button.setBounds(272, 10, 25, 25);
				plus_button.setBorderPainted(false);
				content_panel.add(plus_button);
				plus_button.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						See_user_board new_window = new See_user_board(id);
						new_window.frame.setVisible(true);
						frame.setVisible(false);
					}
				});
				
			}
			if(user_connect.rs.next()) {
				String id = user_connect.rs.getString("id");
				JPanel content_panel2 = new JPanel();
				content_panel2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
				content_panel2.setBackground(new Color(255, 255, 255));
				content_panel2.setBounds(12, 10 + 50, 314, 42);
				User_panel.add(content_panel2);
				content_panel2.setLayout(null);
				
				JLabel user_label2 = new JLabel("");
				user_label2.setBounds(12, 5, 30, 25);
				content_panel2.add(user_label2);
				user_label2.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
				
				JLabel id_label2 = new JLabel(id);
				id_label2.setBounds(46, 7, 80, 25);
				content_panel2.add(id_label2);
				
				JButton plus_button2 = new JButton("");
				plus_button2.setBackground(Color.WHITE);
				plus_button2.setIcon(new ImageIcon(Home.class.getResource("/image/Plus.jpg")));
				plus_button2.setBounds(272, 10, 25, 25);
				plus_button2.setBorderPainted(false);
				content_panel2.add(plus_button2);
				plus_button2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						See_user_board new_window = new See_user_board(id);
						new_window.frame.setVisible(true);
						frame.setVisible(false);
					}
				});
				
			}
			if(user_connect.rs.next()) {
				String id = user_connect.rs.getString("id");
				JPanel content_panel3 = new JPanel();
				content_panel3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
				content_panel3.setBackground(new Color(255, 255, 255));
				content_panel3.setBounds(12, 10 + 100, 314, 42);
				User_panel.add(content_panel3);
				content_panel3.setLayout(null);
				
				JLabel user_label3 = new JLabel("");
				user_label3.setBounds(12, 5, 30, 25);
				content_panel3.add(user_label3);
				user_label3.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
				
				JLabel id_label3 = new JLabel(id);
				id_label3.setBounds(46, 7, 80, 25);
				content_panel3.add(id_label3);
				
				JButton plus_button3 = new JButton("");
				plus_button3.setBackground(Color.WHITE);
				plus_button3.setIcon(new ImageIcon(Home.class.getResource("/image/Plus.jpg")));
				plus_button3.setBounds(272, 10, 25, 25);
				plus_button3.setBorderPainted(false);
				content_panel3.add(plus_button3);
				plus_button3.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						See_user_board new_window = new See_user_board(id);
						new_window.frame.setVisible(true);
						frame.setVisible(false);
					}
				});
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frame.setVisible(true);
	}
}