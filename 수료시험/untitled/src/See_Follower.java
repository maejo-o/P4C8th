//package tweet;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class See_Follower {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					See_Follower window = new See_Follower();
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
	public See_Follower() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/image/logo.png")));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setTitle("twitter");
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel goback_panel = new JPanel();
		goback_panel.setToolTipText("");
		goback_panel.setBackground(new Color(255, 255, 255));
		goback_panel.setBounds(30, 5, 338, 50);
		frame.getContentPane().add(goback_panel);
		goback_panel.setLayout(null);
		
		JButton go_back_button = new JButton("");
		go_back_button.setBounds(5, 15, 25, 25);
		go_back_button.setForeground(new Color(0, 0, 0));
		go_back_button.setBackground(new Color(255, 255, 255));
		go_back_button.setIcon(new ImageIcon(See_Post.class.getResource("/image/go_back.jpg")));
		go_back_button.setBorderPainted(false);
		goback_panel.add(go_back_button);
		go_back_button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Profile new_window = new Profile();
				new_window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		
		JLabel other_user_id_label = new JLabel("SEE FOLLOWER");
		other_user_id_label.setFont(new Font("�������", Font.BOLD, 20));
		other_user_id_label.setBounds(42, 15, 232, 25);
		goback_panel.add(other_user_id_label);
		
		JPanel User_panel = new JPanel();
		User_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		User_panel.setBackground(Color.WHITE);
		User_panel.setBounds(30, 65, 338, 296);
		frame.getContentPane().add(User_panel);
		User_panel.setLayout(null);
		

		String sql = "SELECT * FROM follow WHERE following_id = '" + User.id +  "'";
		DB follow = new DB();
		follow.select(sql);
		
		try {
			if(follow.rs.next()) {

				String id = follow.rs.getString("follower_id");
				
				// activity logic
				sql = "SELECT * FROM user WHERE id = '" + id + "'";
				DB user = new DB();
				user.select(sql);
				user.rs.next();
				int activity = user.rs.getInt("activity");
				
				JPanel content_on_panel = new JPanel();
				content_on_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
				content_on_panel.setBackground(new Color(255, 255, 255));
				content_on_panel.setBounds(12, 10, 314, 42);
				User_panel.add(content_on_panel);
				content_on_panel.setLayout(null);
				
				JLabel user_label = new JLabel("");
				user_label.setBounds(12, 5, 30, 25);
				content_on_panel.add(user_label);
				user_label.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
				
				JLabel id_label = new JLabel(id);
				id_label.setFont(new Font("�������", Font.BOLD, 12));
				id_label.setBounds(46, 7, 80, 25);
				content_on_panel.add(id_label);
				
				JLabel On_label = new JLabel("");
				if(activity == 1) {
					On_label.setIcon(new ImageIcon(See_Follower.class.getResource("/image/on.png")));
				} else if (activity == 0){
					On_label.setIcon(new ImageIcon(See_Follower.class.getResource("/image/off.png")));
				}
				On_label.setBounds(252, 9, 50, 25);
				content_on_panel.add(On_label);
			}
			if(follow.rs.next()) {

				String id = follow.rs.getString("follower_id");
				
				// activity logic
				sql = "SELECT * FROM user WHERE id = '" + id + "'";
				DB user = new DB();
				user.select(sql);
				user.rs.next();
				int activity = user.rs.getInt("activity");
				
				JPanel content_on_panel2 = new JPanel();
				content_on_panel2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
				content_on_panel2.setBackground(new Color(255, 255, 255));
				content_on_panel2.setBounds(12, 10 + 50, 314, 42);
				User_panel.add(content_on_panel2);
				content_on_panel2.setLayout(null);
				
				JLabel user_label2 = new JLabel("");
				user_label2.setBounds(12, 5, 30, 25);
				content_on_panel2.add(user_label2);
				user_label2.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
				
				JLabel id_label2 = new JLabel(id);
				id_label2.setFont(new Font("�������", Font.BOLD, 12));
				id_label2.setBounds(46, 7, 80, 25);
				content_on_panel2.add(id_label2);
				
				JLabel On_label2 = new JLabel("");
				if(activity == 1) {
					On_label2.setIcon(new ImageIcon(See_Follower.class.getResource("/image/on.png")));
				} else if (activity == 0){
					On_label2.setIcon(new ImageIcon(See_Follower.class.getResource("/image/off.png")));
				}
				On_label2.setBounds(252, 9, 50, 25);
				content_on_panel2.add(On_label2);
			}
			if(follow.rs.next()) {

				String id = follow.rs.getString("follower_id");
				
				// activity logic
				sql = "SELECT * FROM user WHERE id = '" + id + "'";
				DB user = new DB();
				user.select(sql);
				user.rs.next();
				int activity = user.rs.getInt("activity");
				
				JPanel content_on_panel3 = new JPanel();
				content_on_panel3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
				content_on_panel3.setBackground(new Color(255, 255, 255));
				content_on_panel3.setBounds(12, 10 + 100, 314, 42);
				User_panel.add(content_on_panel3);
				content_on_panel3.setLayout(null);
				
				JLabel user_label3 = new JLabel("");
				user_label3.setBounds(12, 5, 30, 25);
				content_on_panel3.add(user_label3);
				user_label3.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
				
				JLabel id_label3 = new JLabel(id);
				id_label3.setFont(new Font("�������", Font.BOLD, 12));
				id_label3.setBounds(46, 7, 80, 25);
				content_on_panel3.add(id_label3);
				
				JLabel On_label3 = new JLabel("");
				if(activity == 1) {
					On_label3.setIcon(new ImageIcon(See_Follower.class.getResource("/image/on.png")));
				} else if (activity == 0){
					On_label3.setIcon(new ImageIcon(See_Follower.class.getResource("/image/off.png")));
				}
				On_label3.setBounds(252, 9, 50, 25);
				content_on_panel3.add(On_label3);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
