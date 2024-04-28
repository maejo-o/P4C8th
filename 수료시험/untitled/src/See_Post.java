// package tweet;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;

public class See_Post {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					See_Post window = new See_Post();
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
	public See_Post() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/image/logo.png")));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setTitle("twitter");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel content_panel = new JPanel();
		content_panel.setBounds(12, 54, 314, 117);
		frame.getContentPane().add(content_panel);
		content_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		content_panel.setBackground(new Color(255, 255, 255));
		content_panel.setLayout(null);
		
		JLabel user_label = new JLabel("");
		user_label.setBounds(12, 5, 30, 25);
		content_panel.add(user_label);
		user_label.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
		
		JLabel id_label = new JLabel("id\uB4E4\uC5B4\uAC00\uC57C\uD568");
		id_label.setBounds(45, 5, 80, 25);
		content_panel.add(id_label);
		
		JLabel content_label = new JLabel("\uB0B4\uC6A9 \uB4E4\uC5B4\uAC00\uC57C\uD568");
		content_label.setBounds(12, 33, 290, 46);
		content_panel.add(content_label);
		
		JButton comment_button = new JButton("");
		comment_button.setIcon(new ImageIcon(Home.class.getResource("/image/comment.png")));
		comment_button.setBounds(10, 82, 30, 25);
		comment_button.setBorderPainted(false);
		content_panel.add(comment_button);
		
		JLabel comment_num_label = new JLabel("12");
		comment_num_label.setHorizontalAlignment(SwingConstants.CENTER);
		comment_num_label.setFont(new Font("�������", Font.BOLD, 12));
		comment_num_label.setBounds(35, 82, 25, 25);
		content_panel.add(comment_num_label);
		
		JButton like_button = new JButton("");
		like_button.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
		like_button.setBounds(75, 82, 30, 25);
		like_button.setBorderPainted(false);
		content_panel.add(like_button);
		
		JLabel like_num_label = new JLabel("12");
		like_num_label.setHorizontalAlignment(SwingConstants.CENTER);
		like_num_label.setFont(new Font("�������", Font.BOLD, 12));
		like_num_label.setBounds(100, 82, 25, 25);
		content_panel.add(like_num_label);
		
		JPanel comment_panel = new JPanel();
		comment_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		comment_panel.setBackground(Color.WHITE);
		comment_panel.setBounds(336, 54, 296, 386);
		frame.getContentPane().add(comment_panel);
		comment_panel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 44, 272, 84);
		comment_panel.add(panel);
		panel.setLayout(null);
		
		JLabel comment_user_label = new JLabel("");
		comment_user_label.setIcon(new ImageIcon(See_Post.class.getResource("/image/Profile.png")));
		comment_user_label.setBounds(14, 10, 30, 25);
		panel.add(comment_user_label);
		
		JLabel comment_id_label = new JLabel("id\uB4E4\uC5B4\uAC00\uC57C\uD568");
		comment_id_label.setBounds(47, 10, 80, 25);
		panel.add(comment_id_label);
		
		JLabel comment_content_label = new JLabel("\uB0B4\uC6A9 \uB4E4\uC5B4\uAC00\uC57C\uD568");
		comment_content_label.setBounds(14, 38, 290, 46);
		panel.add(comment_content_label);
		
		JButton comment_comment_button = new JButton("");
		comment_comment_button.setIcon(new ImageIcon(See_Post.class.getResource("/image/comment.png")));
		comment_comment_button.setBorderPainted(false);
		comment_comment_button.setBounds(145, 10, 30, 25);
		panel.add(comment_comment_button);
		
		JLabel comment_comment_num_label = new JLabel("1");
		comment_comment_num_label.setHorizontalAlignment(SwingConstants.CENTER);
		comment_comment_num_label.setFont(new Font("�������", Font.BOLD, 12));
		comment_comment_num_label.setBounds(170, 10, 25, 25);
		panel.add(comment_comment_num_label);
		
		JButton comment_like_button = new JButton("");
		comment_like_button.setIcon(new ImageIcon(See_Post.class.getResource("/image/like.png")));
		comment_like_button.setBorderPainted(false);
		comment_like_button.setBounds(210, 10, 30, 25);
		panel.add(comment_like_button);
		
		JLabel comment_like_num_label = new JLabel("1");
		comment_like_num_label.setHorizontalAlignment(SwingConstants.CENTER);
		comment_like_num_label.setFont(new Font("�������", Font.BOLD, 12));
		comment_like_num_label.setBounds(235, 10, 25, 25);
		panel.add(comment_like_num_label);
		
		JLabel comment_head_label = new JLabel("COMMENT");
		comment_head_label.setBounds(12, 10, 272, 24);
		comment_panel.add(comment_head_label);
		comment_head_label.setHorizontalAlignment(SwingConstants.CENTER);
		comment_head_label.setFont(new Font("�������", Font.BOLD, 13));
		
		JPanel write_comment_panel = new JPanel();
		write_comment_panel.setLayout(null);
		write_comment_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		write_comment_panel.setBackground(Color.WHITE);
		write_comment_panel.setBounds(12, 245, 314, 194);
		frame.getContentPane().add(write_comment_panel);
		
		JLabel writer_comment_label = new JLabel("WRITE COMMENT");
		writer_comment_label.setBounds(12, 21, 290, 24);
		write_comment_panel.add(writer_comment_label);
		writer_comment_label.setHorizontalAlignment(SwingConstants.CENTER);
		writer_comment_label.setFont(new Font("�������", Font.BOLD, 13));
		
		JLabel writer_label = new JLabel("");
		writer_label.setIcon(new ImageIcon(See_Post.class.getResource("/image/Profile.png")));
		writer_label.setBounds(12, 55, 30, 25);
		write_comment_panel.add(writer_label);
		
		JLabel writer_id_label = new JLabel("id\uB4E4\uC5B4\uAC00\uC57C\uD568");
		writer_id_label.setBounds(45, 55, 80, 25);
		write_comment_panel.add(writer_id_label);
		
		JButton reply_button = new JButton("");
		reply_button.setIcon(new ImageIcon(See_Post.class.getResource("/image/Reply.png")));
		reply_button.setBorderPainted(false);
		reply_button.setBounds(222, 154, 80, 30);
		write_comment_panel.add(reply_button);
		
		JTextArea comment_textArea = new JTextArea();
		comment_textArea.setBackground(new Color(192, 192, 192));
		comment_textArea.setBounds(12, 84, 290, 67);
		write_comment_panel.add(comment_textArea);
		
		JPanel goback_panel = new JPanel();
		goback_panel.setForeground(new Color(255, 255, 255));
		goback_panel.setBackground(new Color(255, 255, 255));
		goback_panel.setBounds(12, 10, 163, 37);
		frame.getContentPane().add(goback_panel);
		goback_panel.setLayout(null);
		
		JButton go_back_button = new JButton("");
		go_back_button.setBounds(5, 6, 25, 25);
		go_back_button.setForeground(new Color(0, 0, 0));
		go_back_button.setBackground(new Color(255, 255, 255));
		go_back_button.setIcon(new ImageIcon(See_Post.class.getResource("/image/go_back.jpg")));
		go_back_button.setBorderPainted(false);
		goback_panel.add(go_back_button);
		
		JLabel lblTweet = new JLabel("Tweet");
		lblTweet.setFont(new Font("�������", Font.BOLD, 20));
		lblTweet.setBounds(42, 6, 86, 25);
		goback_panel.add(lblTweet);
		
	}
}
