import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class See_Comment {

	JFrame frame;
	
	/**
	 * Launch the application.
	 */
	
	int post_idx = 0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					See_Comment window = new See_Comment();
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
	public See_Comment(int idx) {
		// post idx 불러오기
		post_idx = idx;
		try {
			initialize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	String previous_window = "";
	
	public See_Comment(int idx, String window) {
		// post idx 불러오기
		post_idx = idx;
		previous_window = window;
		System.out.println(previous_window);
		try {
			initialize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public See_Comment() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
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
		
		JPanel goback_panel = new JPanel();
		goback_panel.setForeground(new Color(255, 255, 255));
		goback_panel.setBackground(new Color(255, 255, 255));
		goback_panel.setBounds(12, 10, 167, 37);
		frame.getContentPane().add(goback_panel);
		goback_panel.setLayout(null);
		
		JButton go_back_button = new JButton("");
		go_back_button.setBounds(5, 6, 25, 25);
		go_back_button.setForeground(new Color(0, 0, 0));
		go_back_button.setBackground(new Color(255, 255, 255));
		go_back_button.setIcon(new ImageIcon(See_Post.class.getResource("/image/go_back.jpg")));
		go_back_button.setBorderPainted(false);
		goback_panel.add(go_back_button);
		go_back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(previous_window.equals("")) {
					Home new_window = new Home();
					new_window.frame.setVisible(true);
					frame.setVisible(false);
				} else if(previous_window.equals("Profile")) {
					Profile new_window = new Profile();
					new_window.frame.setVisible(true);	
					frame.setVisible(false);
				} else {
					See_user_board new_window = new See_user_board(previous_window);
					new_window.frame.setVisible(true);
					frame.setVisible(false);
				}
				
			}
		});
		
		JLabel Post_label = new JLabel("COMMENT");
		Post_label.setFont(new Font("�������", Font.BOLD, 20));
		Post_label.setBounds(42, 6, 137, 25);
		goback_panel.add(Post_label);
		
		/////////////////////// post와 같음
		String sql = "SELECT * FROM post WHERE idx =" + post_idx;
		DB connect = new DB();
		connect.select(sql);
		connect.rs.next();
		
		// comment count
		sql = "SELECT COUNT(*) FROM comment WHERE post_idx = " + connect.rs.getInt("idx");
		DB connect2 = new DB();
		connect2.select(sql);
		connect2.rs.next();
		
		// 기본적인 데이터베이스 
		String id = connect.rs.getString("writter_id");
		String content = connect.rs.getString("content");
		int idx = connect.rs.getInt("idx");
		int count_comment = connect2.rs.getInt(1);
		int count_like = connect.rs.getInt("count_like");
		
		// 패널
		JPanel content_panel = new JPanel();
		content_panel.setBounds(12, 54, 314, 155);
		frame.getContentPane().add(content_panel);
		content_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		content_panel.setBackground(new Color(255, 255, 255));
		content_panel.setLayout(null);
		
		// 프로필 사진
		JLabel user_label = new JLabel("");
		user_label.setBounds(12, 5, 30, 25);
		content_panel.add(user_label);
		user_label.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
		
		// 이름
		JLabel id_label = new JLabel(id);
		id_label.setBounds(45, 5, 80, 25);
		content_panel.add(id_label);
		
		// 본문
		JLabel content_label = new JLabel(content);
		content_label.setBounds(12, 33, 290, 46);
		content_panel.add(content_label);
		
		// 커멘트 사진
		JButton comment_button = new JButton("");
		comment_button.setIcon(new ImageIcon(Home.class.getResource("/image/comment.png")));
		comment_button.setBounds(10, 82, 30, 25);
		content_panel.add(comment_button);
		
		// 커멘트 갯수
		JLabel comment_num_label = new JLabel("" + count_comment);
		comment_num_label.setHorizontalAlignment(SwingConstants.CENTER);
		comment_num_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		comment_num_label.setBounds(35, 82, 25, 25);
		content_panel.add(comment_num_label);
		
		// 좋아요 갯수
		JLabel like_num_label = new JLabel("" + count_like);
		like_num_label.setHorizontalAlignment(SwingConstants.CENTER);
		like_num_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		like_num_label.setBounds(100, 82, 25, 25);
		content_panel.add(like_num_label);
		
		// 좋아요 사진
		JButton like_button = new JButton("");
		// 내가 이미 눌렀으면
		sql = "SELECT COUNT(*) FROM post_like WHERE like_id = '" + User.id + "' AND " + "post_idx = " + idx;
		DB connect3 = new DB();
		connect3.select(sql);
		connect3.rs.next();
		if(connect3.rs.getInt(1) == 0) {
			like_button.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
		} else {
			like_button.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
		}
		like_button.setBounds(75, 82, 30, 25);
		content_panel.add(like_button);
		// 누르면 조건비교해서 하나 올리기, 하나 내리기
		like_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 이미 눌렀는지 확인
				String sql = "SELECT COUNT(*) FROM post_like WHERE like_id = '" + User.id + "' AND " + "post_idx = " + idx;
				DB connect3 = new DB();
				connect3.select(sql);
				try {
					connect3.rs.next();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(connect3.rs.getInt(1) == 0 ) { // 증가
						// 좋아요 증가
						sql = "UPDATE post SET count_like = count_like+1 WHERE idx = " + idx;
						DB connect4 = new DB();
						connect4.update(sql);
						// 행 입력
						sql = "INSERT INTO post_like(like_id, post_idx) values ('" + User.id + "', " + idx + ")";
						connect4 = new DB();
						connect4.update(sql);
						// 하트색 바꾸기
						like_button.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
						// 숫자 하나 올리기
						sql = "SELECT count_like FROM post WHERE idx = " + idx;
						System.out.println(sql);
						DB connect1 = new DB();
						connect1.select(sql);
						connect1.rs.next();
						like_num_label.setText("" + connect1.rs.getInt("count_like"));
					} else { // 감소
						// 좋아요 증가
						sql = "UPDATE post SET count_like = count_like-1 WHERE idx = " + idx;
						DB connect4 = new DB();
						connect4.update(sql);
						// 행 삭제
						sql = "DELETE FROM post_like WHERE like_id = '" + User.id + "' AND post_idx = " + idx;
						System.out.println(sql);
						connect4 = new DB();
						connect4.update(sql);
						// 하트색 바꾸기
						like_button.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
						// 숫자 하나 내리기
						sql = "SELECT count_like FROM post WHERE idx = " + idx;
						System.out.println(sql);
						DB connect1 = new DB();
						connect1.select(sql);
						connect1.rs.next();
						like_num_label.setText("" + connect1.rs.getInt("count_like"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		///////// 입력
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
		
		JLabel writer_id_label = new JLabel(User.id);
		writer_id_label.setBounds(45, 55, 80, 25);
		write_comment_panel.add(writer_id_label);
		
		JTextArea comment_textArea = new JTextArea();
		comment_textArea.setBackground(new Color(192, 192, 192));
		comment_textArea.setBounds(12, 84, 290, 67);
		write_comment_panel.add(comment_textArea);
		
		JButton reply_button = new JButton("");
		reply_button.setIcon(new ImageIcon(See_Post.class.getResource("/image/Reply.png")));
		reply_button.setBorderPainted(false);
		reply_button.setBounds(222, 154, 80, 30);
		write_comment_panel.add(reply_button);
		reply_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content = comment_textArea.getText();
				 // 현재 날짜/시간
		        LocalDateTime now = LocalDateTime.now();
		        // 포맷팅
		        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				if(true) { // not working
					DB connect0 = new DB();
					String sql = "INSERT INTO comment(post_idx, writter_id, content, time) values(" + post_idx + ", '" + User.id  + "', '" +  content +"' , '" + formatedNow + "')";
					System.out.println(sql);
					connect0.insert(sql);
					// refresh and date update
					if(previous_window.equals("")) {
						See_Comment new_window = new See_Comment(post_idx);
						new_window.frame.setVisible(true);	
						frame.setVisible(false);
					} else {
						See_Comment new_window = new See_Comment(post_idx, previous_window);
						new_window.frame.setVisible(true);	
						frame.setVisible(false);
					}
					
					
				}
			}
		});
		
		//////// comment 창	
		JPanel comment_panel = new JPanel();
		comment_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		comment_panel.setBackground(Color.WHITE);
		comment_panel.setBounds(336, 54, 296, 386);
		frame.getContentPane().add(comment_panel);
		comment_panel.setLayout(null);
		
		JLabel comment_head_label = new JLabel("COMMENT");
		comment_head_label.setBounds(12, 10, 272, 24);
		comment_panel.add(comment_head_label);
		comment_head_label.setHorizontalAlignment(SwingConstants.CENTER);
		comment_head_label.setFont(new Font("�������", Font.BOLD, 13));
			
		//////
		// 몇개 불러올 위한 쿼리
		sql = "SELECT * FROM comment WHERE post_idx = " + post_idx + " ORDER BY time DESC LIMIT 0, 3";
		System.out.println(sql);
		DB connect5 = new DB();
		connect5.select(sql);
		
		if(connect5.rs.next()) {
			
			// 기본적인 데이터베이스 
			String comment_id = connect5.rs.getString("writter_id");
			String comment_content = connect5.rs.getString("content");
			int comment_idx = connect5.rs.getInt("idx");
			int comment_count_like = connect5.rs.getInt("count_like");
			int comment_count = connect5.rs.getInt(1);;
			
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
			
			JLabel comment_id_label = new JLabel(comment_id);
			comment_id_label.setBounds(47, 10, 80, 25);
			panel.add(comment_id_label);
			
			JLabel comment_content_label = new JLabel(comment_content);
			comment_content_label.setBounds(14, 38, 290, 46);
			panel.add(comment_content_label);
			
			// 좋아요 숫자
			JLabel comment_like_num_label = new JLabel("" + comment_count_like);
			comment_like_num_label.setHorizontalAlignment(SwingConstants.CENTER);
			comment_like_num_label.setFont(new Font("�������", Font.BOLD, 12));
			comment_like_num_label.setBounds(235, 10, 25, 25);
			panel.add(comment_like_num_label);
			
			// 좋아요 사진
			JButton comment_like_button = new JButton("");
			// 내가 이미 눌렀으면
			sql = "SELECT COUNT(*) FROM comment_like WHERE like_id = '" + User.id + "' AND " + "comment_idx = " + comment_idx;
			DB connect7 = new DB();
			connect7.select(sql);
			connect7.rs.next();
			if(connect7.rs.getInt(1) == 0) {
				comment_like_button.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
			} else {
				comment_like_button.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
			}
			comment_like_button.setIcon(new ImageIcon(See_Post.class.getResource("/image/like.png")));
			comment_like_button.setBorderPainted(false);
			comment_like_button.setBounds(210, 10, 30, 25);
			panel.add(comment_like_button);
			// 누르면 조건비교해서 하나 올리기, 하나 내리기
			comment_like_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 이미 눌렀는지 확인
					String sql = "SELECT COUNT(*) FROM comment_like WHERE like_id = '" + User.id + "' AND " + "comment_idx = " + comment_idx;
					System.out.println(sql);
					DB connect8 = new DB();
					connect8.select(sql);
					try {
						connect8.rs.next();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						if(connect8.rs.getInt(1) == 0 ) { // 증가
							// 좋아요 증가
							sql = "UPDATE comment SET count_like = count_like+1 WHERE post_idx = " + idx + " AND idx = " + comment_idx;
							System.out.println(sql);
							DB connect9 = new DB();
							connect9.update(sql);
							// 행 입력
							sql = "INSERT INTO comment_like(like_id, comment_idx) values ('" + User.id + "', " + comment_idx + ")";
							connect9 = new DB();
							connect9.update(sql);
							// 하트색 바꾸기
							comment_like_button.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
							// 숫자 하나 올리기
							sql = "SELECT count_like FROM comment WHERE idx = " + comment_idx;
							System.out.println(sql);
							DB connect10 = new DB();
							connect10.select(sql);
							connect10.rs.next();
							comment_like_num_label.setText("" + connect10.rs.getInt("count_like"));
						} else { // 감소
							// 좋아요 감소
							sql = "UPDATE comment SET count_like = count_like-1 WHERE post_idx = " + idx + " AND idx = " + comment_idx;
							DB connect11 = new DB();
							connect11.update(sql);
							// 행 삭제
							sql = "DELETE FROM comment_like WHERE like_id = '" + User.id + "' AND comment_idx = " + comment_idx;
							System.out.println(sql);
							connect11 = new DB();
							connect11.update(sql);
							// 하트색 바꾸기
							comment_like_button.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
							// 숫자 하나 내리기
							sql = "SELECT count_like FROM comment WHERE idx = " + comment_idx;
							DB connect13 = new DB();
							connect13.select(sql);
							connect13.rs.next();
							comment_like_num_label.setText("" + connect13.rs.getInt("count_like"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
		}
		//////////
		if(connect5.rs.next()) {
			// 기본적인 데이터베이스 
			String comment_id = connect5.rs.getString("writter_id");
			String comment_content = connect5.rs.getString("content");
			int comment_idx = connect5.rs.getInt("idx");
			int comment_count_like = connect5.rs.getInt("count_like");
			int comment_count = connect5.rs.getInt(1);;
			
			JPanel panel2 = new JPanel();
			panel2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
			panel2.setBackground(new Color(255, 255, 255));
			panel2.setBounds(12, 44 + 100, 272, 84);
			comment_panel.add(panel2);
			panel2.setLayout(null);
			
			JLabel comment_user_label2 = new JLabel("");
			comment_user_label2.setIcon(new ImageIcon(See_Post.class.getResource("/image/Profile.png")));
			comment_user_label2.setBounds(14, 10, 30, 25);
			panel2.add(comment_user_label2);
			
			JLabel comment_id_label2 = new JLabel(comment_id);
			comment_id_label2.setBounds(47, 10, 80, 25);
			panel2.add(comment_id_label2);
			
			JLabel comment_content_label2 = new JLabel(comment_content);
			comment_content_label2.setBounds(14, 38, 290, 46);
			panel2.add(comment_content_label2);
			
			// 좋아요 숫자
			JLabel comment_like_num_label2 = new JLabel("" + comment_count_like);
			comment_like_num_label2.setHorizontalAlignment(SwingConstants.CENTER);
			comment_like_num_label2.setFont(new Font("�������", Font.BOLD, 12));
			comment_like_num_label2.setBounds(235, 10, 25, 25);
			panel2.add(comment_like_num_label2);
			
			// 좋아요 사진
			JButton comment_like_button2 = new JButton("");
			// 내가 이미 눌렀으면
			sql = "SELECT COUNT(*) FROM comment_like WHERE like_id = '" + User.id + "' AND " + "comment_idx = " + comment_idx;
			DB connect7 = new DB();
			connect7.select(sql);
			connect7.rs.next();
			if(connect7.rs.getInt(1) == 0) {
				comment_like_button2.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
			} else {
				comment_like_button2.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
			}
			comment_like_button2.setIcon(new ImageIcon(See_Post.class.getResource("/image/like.png")));
			comment_like_button2.setBorderPainted(false);
			comment_like_button2.setBounds(210, 10, 30, 25);
			panel2.add(comment_like_button2);
			// 누르면 조건비교해서 하나 올리기, 하나 내리기
			comment_like_button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 이미 눌렀는지 확인
					String sql = "SELECT COUNT(*) FROM comment_like WHERE like_id = '" + User.id + "' AND " + "comment_idx = " + comment_idx;
					System.out.println(sql);
					DB connect8 = new DB();
					connect8.select(sql);
					try {
						connect8.rs.next();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						if(connect8.rs.getInt(1) == 0 ) { // 증가
							// 좋아요 증가
							sql = "UPDATE comment SET count_like = count_like+1 WHERE post_idx = " + idx + " AND idx = " + comment_idx;
							System.out.println(sql);
							DB connect9 = new DB();
							connect9.update(sql);
							// 행 입력
							sql = "INSERT INTO comment_like(like_id, comment_idx) values ('" + User.id + "', " + comment_idx + ")";
							connect9 = new DB();
							connect9.update(sql);
							// 하트색 바꾸기
							comment_like_button2.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
							// 숫자 하나 올리기
							sql = "SELECT count_like FROM comment WHERE idx = " + comment_idx;
							System.out.println(sql);
							DB connect10 = new DB();
							connect10.select(sql);
							connect10.rs.next();
							comment_like_num_label2.setText("" + connect10.rs.getInt("count_like"));
						} else { // 감소
							// 좋아요 감소
							sql = "UPDATE comment SET count_like = count_like-1 WHERE post_idx = " + idx + " AND idx = " + comment_idx;
							DB connect11 = new DB();
							connect11.update(sql);
							// 행 삭제
							sql = "DELETE FROM comment_like WHERE like_id = '" + User.id + "' AND comment_idx = " + comment_idx;
							System.out.println(sql);
							connect11 = new DB();
							connect11.update(sql);
							// 하트색 바꾸기
							comment_like_button2.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
							// 숫자 하나 내리기
							sql = "SELECT count_like FROM comment WHERE idx = " + comment_idx;
							DB connect13 = new DB();
							connect13.select(sql);
							connect13.rs.next();
							comment_like_num_label2.setText("" + connect13.rs.getInt("count_like"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
		}
///////
			if(connect5.rs.next()) {
				
				// 기본적인 데이터베이스 
				String comment_id = connect5.rs.getString("writter_id");
				String comment_content = connect5.rs.getString("content");
				int comment_idx = connect5.rs.getInt("idx");
				int comment_count_like = connect5.rs.getInt("count_like");
				int comment_count = connect5.rs.getInt(1);;
				
				JPanel panel3 = new JPanel();
				panel3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
				panel3.setBackground(new Color(255, 255, 255));
				panel3.setBounds(12, 44 + 200, 272, 84);
				comment_panel.add(panel3);
				panel3.setLayout(null);
				
				JLabel comment_user_label3 = new JLabel("");
				comment_user_label3.setIcon(new ImageIcon(See_Post.class.getResource("/image/Profile.png")));
				comment_user_label3.setBounds(14, 10, 30, 25);
				panel3.add(comment_user_label3);
				
				JLabel comment_id_label3 = new JLabel(comment_id);
				comment_id_label3.setBounds(47, 10, 80, 25);
				panel3.add(comment_id_label3);
				
				JLabel comment_content_label3 = new JLabel(comment_content);
				comment_content_label3.setBounds(14, 38, 290, 46);
				panel3.add(comment_content_label3);
				
				// 좋아요 숫자
				JLabel comment_like_num_label3 = new JLabel("" + comment_count_like);
				comment_like_num_label3.setHorizontalAlignment(SwingConstants.CENTER);
				comment_like_num_label3.setFont(new Font("�������", Font.BOLD, 12));
				comment_like_num_label3.setBounds(235, 10, 25, 25);
				panel3.add(comment_like_num_label3);
				
				// 좋아요 사진
				JButton comment_like_button3 = new JButton("");
				// 내가 이미 눌렀으면
				sql = "SELECT COUNT(*) FROM comment_like WHERE like_id = '" + User.id + "' AND " + "comment_idx = " + comment_idx;
				DB connect7 = new DB();
				connect7.select(sql);
				connect7.rs.next();
				if(connect7.rs.getInt(1) == 0) {
					comment_like_button3.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
				} else {
					comment_like_button3.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
				}
				comment_like_button3.setIcon(new ImageIcon(See_Post.class.getResource("/image/like.png")));
				comment_like_button3.setBorderPainted(false);
				comment_like_button3.setBounds(210, 10, 30, 25);
				panel3.add(comment_like_button3);
				// 누르면 조건비교해서 하나 올리기, 하나 내리기
				comment_like_button3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 이미 눌렀는지 확인
						String sql = "SELECT COUNT(*) FROM comment_like WHERE like_id = '" + User.id + "' AND " + "comment_idx = " + comment_idx;
						System.out.println(sql);
						DB connect8 = new DB();
						connect8.select(sql);
						try {
							connect8.rs.next();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							if(connect8.rs.getInt(1) == 0 ) { // 증가
								// 좋아요 증가
								sql = "UPDATE comment SET count_like = count_like+1 WHERE post_idx = " + idx + " AND idx = " + comment_idx;
								System.out.println(sql);
								DB connect9 = new DB();
								connect9.update(sql);
								// 행 입력
								sql = "INSERT INTO comment_like(like_id, comment_idx) values ('" + User.id + "', " + comment_idx + ")";
								connect9 = new DB();
								connect9.update(sql);
								// 하트색 바꾸기
								comment_like_button3.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
								// 숫자 하나 올리기
								sql = "SELECT count_like FROM comment WHERE idx = " + comment_idx;
								System.out.println(sql);
								DB connect10 = new DB();
								connect10.select(sql);
								connect10.rs.next();
								comment_like_num_label3.setText("" + connect10.rs.getInt("count_like"));
							} else { // 감소
								// 좋아요 감소
								sql = "UPDATE comment SET count_like = count_like-1 WHERE post_idx = " + idx + " AND idx = " + comment_idx;
								DB connect11 = new DB();
								connect11.update(sql);
								// 행 삭제
								sql = "DELETE FROM comment_like WHERE like_id = '" + User.id + "' AND comment_idx = " + comment_idx;
								System.out.println(sql);
								connect11 = new DB();
								connect11.update(sql);
								// 하트색 바꾸기
								comment_like_button3.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
								// 숫자 하나 내리기
								sql = "SELECT count_like FROM comment WHERE idx = " + comment_idx;
								DB connect13 = new DB();
								connect13.select(sql);
								connect13.rs.next();
								comment_like_num_label3.setText("" + connect13.rs.getInt("count_like"));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			
					}
				});
			}
		}
	}

