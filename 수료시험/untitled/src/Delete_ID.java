import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Delete_ID {

	JFrame frame;
	private HintTextField Password_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_ID window = new Delete_ID();
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
	public Delete_ID() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/image/logo.png")));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setTitle("twitter");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel Password_label = new JLabel("");
		Password_label.setIcon(new ImageIcon(Login.class.getResource("/image/password.jpg")));
		Password_label.setHorizontalAlignment(SwingConstants.CENTER);
		Password_label.setForeground(Color.WHITE);
		Password_label.setFont(new Font("�������", Font.BOLD, 25));
		Password_label.setBackground(Color.WHITE);
		Password_label.setBounds(139, 187, 50, 50);
		frame.getContentPane().add(Password_label);
		
		Password_textField = new HintTextField("Password");
		Password_textField.setForeground(Color.BLACK);
		Password_textField.setColumns(10);
		Password_textField.setBackground(Color.WHITE);
		Password_textField.setBounds(201, 192, 155, 45);
		frame.getContentPane().add(Password_textField);
		
		JButton btnNewButton = new JButton("DELETE ID");
		btnNewButton.setForeground(new Color(0, 191, 255));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("�������", Font.BOLD, 20));
		btnNewButton.setBounds(139, 257, 217, 36);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw = Password_textField.getText();
				
				if(!pw.equals("Password")) {
					String sql = "SELECT COUNT(*) FROM user WHERE id = '" + User.id + "' AND pw = '" + pw + "'";
					DB connect1 = new DB();
					connect1.select(sql);
					try {
						connect1.rs.next();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						if(connect1.rs.getInt(1) == 1) { // 성공
							sql = "DELETE FROM user WHERE id = '" + User.id + "' AND pw = '" + pw + "'";
							System.out.println(sql);
							DB connect2 = new DB();
							connect2.update(sql);
							Login new_window = new Login();
							new_window.frame.setVisible(true);
							frame.setVisible(false);
							
						} else { // 실패
							Password_textField.setText("Password");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Password_textField.setText("Password");
				}
			}
		});
		
		JButton btnRegister = new JButton("CANCLE");
		btnRegister.setForeground(new Color(0, 191, 255));
		btnRegister.setFont(new Font("�������", Font.BOLD, 20));
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setBounds(139, 307, 217, 36);
		frame.getContentPane().add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile new_window = new Profile();
				new_window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JLabel background_label = new JLabel("");
		background_label.setBackground(new Color(255, 255, 255));
		background_label.setForeground(new Color(255, 255, 255));
		background_label.setIcon(new ImageIcon(Login.class.getResource("/image/login_background.jpg")));
		background_label.setBounds(0, 0, 494, 471);
		frame.getContentPane().add(background_label);
	}

}
