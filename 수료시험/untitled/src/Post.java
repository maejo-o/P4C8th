// package tweet;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Post {
	Post() {
		build();
	}
	
	void build() {
		
		JFrame frame;
		frame = new JFrame();
		
		JPanel post_panel = new JPanel();
		post_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		post_panel.setBackground(Color.WHITE);
		post_panel.setBounds(144, 225, 338, 436);
		frame.getContentPane().add(post_panel);
		post_panel.setLayout(null);
		
		JPanel content_panel = new JPanel();
		content_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		content_panel.setBackground(new Color(255, 255, 255));
		content_panel.setBounds(12, 10, 314, 117);
		post_panel.add(content_panel);
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
		content_panel.add(comment_button);
		
		JLabel comment_num_label = new JLabel("12");
		comment_num_label.setHorizontalAlignment(SwingConstants.CENTER);
		comment_num_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		comment_num_label.setBounds(35, 82, 25, 25);
		content_panel.add(comment_num_label);
		
		JButton like_button = new JButton("");
		like_button.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
		like_button.setBounds(75, 82, 30, 25);
		content_panel.add(like_button);
		
		JLabel like_num_label = new JLabel("12");
		like_num_label.setHorizontalAlignment(SwingConstants.CENTER);
		like_num_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		like_num_label.setBounds(100, 82, 25, 25);
		content_panel.add(like_num_label);
	}
}
