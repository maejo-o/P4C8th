// package tweet;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Login {

	JFrame frame;
	private HintTextField ID_textField;
	private HintTextField Password_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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
		
		
		JLabel ID_label = new JLabel("");
		ID_label.setBounds(139, 171, 50, 50);
		frame.getContentPane().add(ID_label);
		ID_label.setIcon(new ImageIcon(Login.class.getResource("/image/ID.jpg")));
		ID_label.setFont(new Font("�������", Font.BOLD, 25));
		ID_label.setForeground(new Color(255, 255, 255));
		ID_label.setBackground(new Color(255, 255, 255));
		ID_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel Password_label = new JLabel("");
		Password_label.setIcon(new ImageIcon(Login.class.getResource("/image/password.jpg")));
		Password_label.setHorizontalAlignment(SwingConstants.CENTER);
		Password_label.setForeground(Color.WHITE);
		Password_label.setFont(new Font("�������", Font.BOLD, 25));
		Password_label.setBackground(Color.WHITE);
		Password_label.setBounds(139, 234, 50, 50);
		frame.getContentPane().add(Password_label);
		
		ID_textField = new HintTextField("ID");
		ID_textField.setBounds(201, 171, 155, 45);
		frame.getContentPane().add(ID_textField);
		ID_textField.setForeground(new Color(0, 0, 0));
		ID_textField.setBackground(new Color(255, 255, 255));
		ID_textField.setColumns(10);
		
		Password_textField = new HintTextField("Password");
		Password_textField.setForeground(Color.BLACK);
		Password_textField.setColumns(10);
		Password_textField.setBackground(Color.WHITE);
		Password_textField.setBounds(201, 245, 155, 45);
		frame.getContentPane().add(Password_textField);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setForeground(new Color(0, 191, 255));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("�������", Font.BOLD, 20));
		btnNewButton.setBounds(139, 310, 217, 36);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = ID_textField.getText();
				String pw = Password_textField.getText();
				
				boolean id_is_malicious = Security.sqlRegexChecker(id);
				boolean pw_is_malicious = Security.sqlRegexChecker(pw);
//				boolean id_is_malicious = false;
//				boolean pw_is_malicious = false;
				DB connect = new DB();
				if(!id.equals("ID") && !pw.equals("Password") && id_is_malicious == false && pw_is_malicious == false) {
					String sql = "SELECT * FROM user WHERE ID='" + id +"' and PW = '" + pw + "'";
					connect.select(sql);
					try {
						if(connect.rs.next()) {
							// 정보 가지고 오기
							String uid = connect.rs.getString("id");
							String name = connect.rs.getString("name");
							// 활동 상태 업데이트
							DB connect1 = new DB();
							sql = "UPDATE user SET activity = 1 WHERE id = '" + uid + "'";
							connect1.update(sql);
							// 세션 만들기
							User current_user = new User();
							current_user.insert_info(uid, name);
							// 창 새로 띄우
							Home new_window = new Home();
							new_window.frame.setVisible(true);
							frame.setVisible(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setForeground(new Color(0, 191, 255));
		btnRegister.setFont(new Font("�������", Font.BOLD, 20));
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setBounds(139, 360, 217, 36);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register new_window = new Register();
				new_window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		frame.getContentPane().add(btnRegister);
		
		JLabel background_label = new JLabel("");
		background_label.setBackground(new Color(255, 255, 255));
		background_label.setForeground(new Color(255, 255, 255));
		background_label.setIcon(new ImageIcon(Login.class.getResource("/image/login_background.jpg")));
		background_label.setBounds(0, 0, 494, 471);
		frame.getContentPane().add(background_label);
	}
}
