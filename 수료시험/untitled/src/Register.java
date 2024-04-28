// package tweet;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Register {

	JFrame frame;
	private HintTextField ID_textField;
	private HintTextField Password_textField;
	private HintTextField Name_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
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
		
		JLabel Name_label = new JLabel("");
		Name_label.setIcon(new ImageIcon(Register.class.getResource("/image/name.jpg")));
		Name_label.setHorizontalAlignment(SwingConstants.CENTER);
		Name_label.setForeground(Color.WHITE);
		Name_label.setFont(new Font("�������", Font.BOLD, 25));
		Name_label.setBackground(Color.WHITE);
		Name_label.setBounds(139, 138, 50, 50);
		frame.getContentPane().add(Name_label);
		
		JLabel ID_label = new JLabel("");
		ID_label.setBounds(139, 195, 50, 50);
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
		Password_label.setBounds(139, 255, 50, 50);
		frame.getContentPane().add(Password_label);
		
		ID_textField = new HintTextField("ID");
		ID_textField.setBounds(201, 209, 155, 28);
		frame.getContentPane().add(ID_textField);
		ID_textField.setForeground(new Color(0, 0, 0));
		ID_textField.setBackground(new Color(255, 255, 255));
		ID_textField.setColumns(10);
		
		Password_textField = new HintTextField("Passowrd");
		Password_textField.setForeground(Color.BLACK);
		Password_textField.setColumns(10);
		Password_textField.setBackground(Color.WHITE);
		Password_textField.setBounds(201, 269, 155, 28);
		frame.getContentPane().add(Password_textField);
		
		Name_textField = new HintTextField("Name");
		Name_textField.setForeground(Color.BLACK);
		Name_textField.setColumns(10);
		Name_textField.setBackground(Color.WHITE);
		Name_textField.setBounds(201, 149, 155, 28);
		frame.getContentPane().add(Name_textField);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setForeground(new Color(0, 191, 255));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("�������", Font.BOLD, 20));
		btnNewButton.setBounds(139, 310, 217, 36);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login new_window = new Login();
				new_window.frame.setVisible(true);
				frame.setVisible(false);
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
				String name = Name_textField.getText();
				String id = ID_textField.getText();
				String pw = Password_textField.getText();
				if(!name.equals("Name") && !id.equals("ID") && !pw.equals("Password")) {
					DB connect = new DB();
					connect.insert("INSERT INTO user(id, pw, name) values('" + id + "', '" + pw + "', '" + name + "')");
					Login new_window = new Login();
					new_window.frame.setVisible(true);
					frame.setVisible(false);
				}
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
