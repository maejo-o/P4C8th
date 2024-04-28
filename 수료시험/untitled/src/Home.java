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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Home {

	JFrame frame;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Home() {
		initialize();
	}


	private void initialize() {
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
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE user SET activity = 0 WHERE id = '" + User.id + "'";
				System.out.println(sql);
				DB connect_logout = new DB();
				connect_logout.update(sql);
				Login new_window = new Login();
				new_window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JLabel Home_label = new JLabel("");
		Home_label.setIcon(new ImageIcon(Home.class.getResource("/image/Home_bold.png")));
		Home_label.setBounds(12, 10, 30, 25);
		manu_panel.add(Home_label);
		
		JLabel Explore_label = new JLabel("");
		Explore_label.setIcon(new ImageIcon(Home.class.getResource("/image/Search.png")));
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
		
		JLabel lblNewLabel = new JLabel(" Home");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 26));
		lblNewLabel.setBounds(0, 0, 100, 50);
		status_panel.add(lblNewLabel);
		
		JPanel post_write_panel = new JPanel();
		post_write_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		post_write_panel.setBackground(Color.WHITE);
		post_write_panel.setBounds(144, 60, 338, 152);
		frame.getContentPane().add(post_write_panel);
		post_write_panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(192, 192, 192));
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		textArea.setBounds(10, 31, 314, 84);
		post_write_panel.add(textArea);
		
		JButton Tweet_button = new JButton("");
		Tweet_button.setIcon(new ImageIcon(Home.class.getResource("/image/tweet.png")));
		Tweet_button.setFont(new Font("나눔고딕", Font.BOLD, 10));
		Tweet_button.setBounds(246, 117, 80, 25);
		Tweet_button.setBorderPainted(false);
		post_write_panel.add(Tweet_button);
		Tweet_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String content = textArea.getText();
						 // 현재 날짜/시간
				        LocalDateTime now = LocalDateTime.now();
				        // 포맷팅
				        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						if(true) { // not working
							DB connect = new DB();
							String sql = "INSERT INTO post(writter_id, content, time) values('" + User.id + "', '" +  content +"' , '" + formatedNow + "')";
							System.out.println(sql);
							connect.insert(sql);
							textArea.setText("");
							// refresh and date update
							Home new_window = new Home();
							new_window.frame.setVisible(true);	
							frame.setVisible(false);
						}
					}
				});
		
		JLabel Waht_label = new JLabel("What's happening?");
		Waht_label.setFont(new Font("나눔고딕", Font.BOLD, 10));
		Waht_label.setBounds(10, 7, 142, 20);
		post_write_panel.add(Waht_label);
		
		JPanel post_panel = new JPanel();
		post_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		post_panel.setBackground(Color.WHITE);
		post_panel.setBounds(144, 225, 338, 436);
		frame.getContentPane().add(post_panel);
		post_panel.setLayout(null);
		
		String sql = "SELECT COUNT(*) FROM follow WHERE follower_id = '" + User.id + "'";
		DB count = new DB();
		count.select(sql);
		int SIZE = 0;
		try {
			count.rs.next();
			SIZE = count.rs.getInt(1);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		sql = "SELECT * FROM follow WHERE follower_id = '" + User.id + "'";
		DB union = new DB();
		union.select(sql);
		
		String main_sql = "SELECT * FROM post WHERE writter_id = '" + User.id + "'";
		try {
			for(int i = 0 ; i < SIZE ; i++) {
				union.rs.next();
				main_sql += " UNION SELECT * FROM post WHERE writter_id = '" + union.rs.getString("following_id") + "'";
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		main_sql += " ORDER BY time DESC LIMIT 0, 3";
		System.out.println(main_sql);
		DB connect = new DB();
		connect.select(main_sql);
		
		//////////////
		try {
			if(connect.rs.next()) {
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
				
				// 페널
				JPanel content_panel = new JPanel();
				content_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
				content_panel.setBackground(new Color(255, 255, 255));
				content_panel.setBounds(12, 10, 314, 117);
				post_panel.add(content_panel);
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
				comment_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						See_Comment new_window = new See_Comment(idx);
						new_window.frame.setVisible(true);
						frame.setVisible(false);
					}
				});
		
				
				// 커멘트 갯수
				JLabel comment_num_label = new JLabel("" + count_comment);
				comment_num_label.setHorizontalAlignment(SwingConstants.CENTER);
				comment_num_label.setFont(new Font("나눔고딕", Font.BOLD, 10));
				comment_num_label.setBounds(35, 82, 25, 25);
				content_panel.add(comment_num_label);
				
				// 좋아요 갯수
				sql = "SELECT count_like FROM post WHERE idx = " + connect.rs.getInt("idx");
				DB connect1 = new DB();
				connect1.select(sql);
				connect1.rs.next();
						
				JLabel like_num_label = new JLabel("" + connect1.rs.getInt("count_like"));
				like_num_label.setHorizontalAlignment(SwingConstants.CENTER);
				like_num_label.setFont(new Font("나눔고딕", Font.BOLD, 10));
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
								// 좋아요 감소
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
			}
			////////////////////////////////
		
			if(connect.rs.next()) {
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
				
				// 페널
				JPanel content_panel2 = new JPanel();
				content_panel2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
				content_panel2.setBackground(new Color(255, 255, 255));
				content_panel2.setBounds(12, 10 + 125, 314, 117);
				post_panel.add(content_panel2);
				content_panel2.setLayout(null);
				
				// 프로필 사진
				JLabel user_label2 = new JLabel("");
				user_label2.setBounds(12, 5, 30, 25);
				content_panel2.add(user_label2);
				user_label2.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
				
				// 이름
				JLabel id_label2 = new JLabel(id);
				id_label2.setBounds(45, 5, 80, 25);
				content_panel2.add(id_label2);
				
				// 본문
				JLabel content_label2 = new JLabel(content);
				content_label2.setBounds(12, 33, 290, 46);
				content_panel2.add(content_label2);
				
				// 커멘트 사진
				JButton comment_button2 = new JButton("");
				comment_button2.setIcon(new ImageIcon(Home.class.getResource("/image/comment.png")));
				comment_button2.setBounds(10, 82, 30, 25);
				content_panel2.add(comment_button2);
				comment_button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						See_Comment new_window = new See_Comment(idx);
						new_window.frame.setVisible(true);
						frame.setVisible(false);
					}
				});
		
				
				// 커멘트 갯수
				JLabel comment_num_label2 = new JLabel("" + count_comment);
				comment_num_label2.setHorizontalAlignment(SwingConstants.CENTER);
				comment_num_label2.setFont(new Font("나눔고딕", Font.BOLD, 10));
				comment_num_label2.setBounds(35, 82, 25, 25);
				content_panel2.add(comment_num_label2);
				
				// 좋아요 갯수
				sql = "SELECT count_like FROM post WHERE idx = " + connect.rs.getInt("idx");
				DB connect1 = new DB();
				connect1.select(sql);
				connect1.rs.next();
						
				JLabel like_num_label2 = new JLabel("" + connect1.rs.getInt("count_like"));
				like_num_label2.setHorizontalAlignment(SwingConstants.CENTER);
				like_num_label2.setFont(new Font("나눔고딕", Font.BOLD, 10));
				like_num_label2.setBounds(100, 82, 25, 25);
				content_panel2.add(like_num_label2);
				
				// 좋아요 사진
				JButton like_button2 = new JButton("");
				// 내가 이미 눌렀으면
				sql = "SELECT COUNT(*) FROM post_like WHERE like_id = '" + User.id + "' AND " + "post_idx = " + idx;
				DB connect3 = new DB();
				connect3.select(sql);
				connect3.rs.next();
				if(connect3.rs.getInt(1) == 0) {
					like_button2.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
				} else {
					like_button2.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
				}
				like_button2.setBounds(75, 82, 30, 25);
				content_panel2.add(like_button2);
				// 누르면 조건비교해서 하나 올리기, 하나 내리기
				like_button2.addActionListener(new ActionListener() {
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
								like_button2.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
								// 숫자 하나 올리기
								sql = "SELECT count_like FROM post WHERE idx = " + idx;
								System.out.println(sql);
								DB connect1 = new DB();
								connect1.select(sql);
								connect1.rs.next();
								like_num_label2.setText("" + connect1.rs.getInt("count_like"));
							} else { // 감소
								// 좋아요 감소
								sql = "UPDATE post SET count_like = count_like-1 WHERE idx = " + idx;
								DB connect4 = new DB();
								connect4.update(sql);
								// 행 삭제
								sql = "DELETE FROM post_like WHERE like_id = '" + User.id + "' AND post_idx = " + idx;
								System.out.println(sql);
								connect4 = new DB();
								connect4.update(sql);
								// 하트색 바꾸기
								like_button2.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
								// 숫자 하나 내리기
								sql = "SELECT count_like FROM post WHERE idx = " + idx;
								DB connect1 = new DB();
								connect1.select(sql);
								connect1.rs.next();
								like_num_label2.setText("" + connect1.rs.getInt("count_like"));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
			}
			if(connect.rs.next()) {
				// NAME으로 출력 해주는 과정
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
				
				// 페널
				JPanel content_panel3 = new JPanel();
				content_panel3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
				content_panel3.setBackground(new Color(255, 255, 255));
				content_panel3.setBounds(12, 10 + 250, 314, 117);
				post_panel.add(content_panel3);
				content_panel3.setLayout(null);
				
				// 프로필 사진
				JLabel user_label3 = new JLabel("");
				user_label3.setBounds(12, 5, 30, 25);
				content_panel3.add(user_label3);
				user_label3.setIcon(new ImageIcon(Home.class.getResource("/image/Profile.png")));
				
				// 이름
				JLabel id_label3 = new JLabel(id);
				id_label3.setBounds(45, 5, 80, 25);
				content_panel3.add(id_label3);
				
				// 본문
				JLabel content_label3 = new JLabel(content);
				content_label3.setBounds(12, 33, 290, 46);
				content_panel3.add(content_label3);
				
				// 커멘트 사진
				JButton comment_button3 = new JButton("");
				comment_button3.setIcon(new ImageIcon(Home.class.getResource("/image/comment.png")));
				comment_button3.setBounds(10, 82, 30, 25);
				content_panel3.add(comment_button3);
				comment_button3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						See_Comment new_window = new See_Comment(idx);
						new_window.frame.setVisible(true);
						frame.setVisible(false);
					}
				});
		
				
				// 커멘트 갯수
				JLabel comment_num_label3 = new JLabel("" + count_comment);
				comment_num_label3.setHorizontalAlignment(SwingConstants.CENTER);
				comment_num_label3.setFont(new Font("나눔고딕", Font.BOLD, 10));
				comment_num_label3.setBounds(35, 82, 25, 25);
				content_panel3.add(comment_num_label3);
				
				// 좋아요 갯수
				sql = "SELECT count_like FROM post WHERE idx = " + connect.rs.getInt("idx");
				DB connect1 = new DB();
				connect1.select(sql);
				connect1.rs.next();
						
				JLabel like_num_label3 = new JLabel("" + connect1.rs.getInt("count_like"));
				like_num_label3.setHorizontalAlignment(SwingConstants.CENTER);
				like_num_label3.setFont(new Font("나눔고딕", Font.BOLD, 10));
				like_num_label3.setBounds(100, 82, 25, 25);
				content_panel3.add(like_num_label3);
				
				// 좋아요 사진
				JButton like_button3 = new JButton("");
				// 내가 이미 눌렀으면
				sql = "SELECT COUNT(*) FROM post_like WHERE like_id = '" + User.id + "' AND " + "post_idx = " + idx;
				DB connect3 = new DB();
				connect3.select(sql);
				connect3.rs.next();
				if(connect3.rs.getInt(1) == 0) {
					like_button3.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
				} else {
					like_button3.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
				}
				like_button3.setBounds(75, 82, 30, 25);
				content_panel3.add(like_button3);
				// 누르면 조건비교해서 하나 올리기, 하나 내리기
				like_button3.addActionListener(new ActionListener() {
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
								like_button3.setIcon(new ImageIcon(Home.class.getResource("/image/red_like.jpg")));
								// 숫자 하나 올리기
								sql = "SELECT count_like FROM post WHERE idx = " + idx;
								System.out.println(sql);
								DB connect1 = new DB();
								connect1.select(sql);
								connect1.rs.next();
								like_num_label3.setText("" + connect1.rs.getInt("count_like"));
							} else { // 감소
								// 좋아요 감소
								sql = "UPDATE post SET count_like = count_like-1 WHERE idx = " + idx;
								DB connect4 = new DB();
								connect4.update(sql);
								// 행 삭제
								sql = "DELETE FROM post_like WHERE like_id = '" + User.id + "' AND post_idx = " + idx;
								System.out.println(sql);
								connect4 = new DB();
								connect4.update(sql);
								// 하트색 바꾸기
								like_button3.setIcon(new ImageIcon(Home.class.getResource("/image/like.png")));
								// 숫자 하나 내리기
								sql = "SELECT count_like FROM post WHERE idx = " + idx;
								DB connect1 = new DB();
								connect1.select(sql);
								connect1.rs.next();
								like_num_label3.setText("" + connect1.rs.getInt("count_like"));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
