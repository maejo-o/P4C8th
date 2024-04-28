//package tweet;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
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

public class See_user_board {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					See_user_board window = new See_user_board();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public See_user_board(){
		initialize();
	}
	
	String other_user_id = "";
	public See_user_board(String id){
		other_user_id = id;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize(){
		DB connect = new DB();
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/image/logo.png")));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setTitle("twitter");
		frame.setBounds(100, 100, 400, 700);
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
				Explore f0 = new Explore();
				frame.setVisible(false);
			}
		});
		
		JLabel other_user_id_label = new JLabel(other_user_id);
		other_user_id_label.setFont(new Font("나눔고딕", Font.BOLD, 20));
		other_user_id_label.setBounds(42, 15, 232, 25);
		goback_panel.add(other_user_id_label);
		
		JPanel user_info_panel = new JPanel();
		user_info_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		user_info_panel.setBackground(Color.WHITE);
		user_info_panel.setBounds(30, 60, 338, 99);
		frame.getContentPane().add(user_info_panel);
		user_info_panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Profile.class.getResource("/image/Profile_bold.png")));
		label.setBounds(12, 10, 30, 25);
		user_info_panel.add(label);
		
		connect.select("select * from user where id = \"" + other_user_id + "\"");
		
		String name = null;
		int following = 0, follower = 0;
		
		try {
			if(connect.rs.next()) {
				name = connect.rs.getString( "name" );

			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		connect.select("select count(*) as following from follow where follower_id = \"" + other_user_id + "\"");
		
		try {
			if(connect.rs.next()) {
				following = connect.rs.getInt("following");
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		connect.select("select count(*) as follower from follow where following_id = \"" + other_user_id + "\"");
		
		try {
			if(connect.rs.next()) {
				follower = connect.rs.getInt("follower");
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JLabel Name_label = new JLabel(name);
		Name_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		Name_label.setBounds(50, 5, 45, 30);
		user_info_panel.add(Name_label);
		
		JLabel ID_label = new JLabel(other_user_id);
		ID_label.setForeground(Color.GRAY);
		ID_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		ID_label.setBounds(12, 40, 104, 25);
		user_info_panel.add(ID_label);
		
		JLabel lblNewLabel = new JLabel(Integer.toString(following));
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(160, 10, 20, 20);
		user_info_panel.add(lblNewLabel);
		
		JLabel label_3 = new JLabel(Integer.toString(follower));
		label_3.setForeground(Color.GRAY);
		label_3.setFont(new Font("나눔고딕", Font.BOLD, 12));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(160, 40, 20, 20);
		user_info_panel.add(label_3);
		
		JLabel lblFollowing = new JLabel("Following");
		lblFollowing.setHorizontalAlignment(SwingConstants.LEFT);
		lblFollowing.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblFollowing.setBounds(180, 10, 77, 20);
		user_info_panel.add(lblFollowing);
		
		JLabel lblFollower = new JLabel("Follower");
		lblFollower.setHorizontalAlignment(SwingConstants.LEFT);
		lblFollower.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblFollower.setBounds(180, 40, 77, 20);
		user_info_panel.add(lblFollower);
		
		JButton button_1 = new JButton("");
		DB follow = new DB();
		follow.select("select * from follow where follower_id = \"" + User.id + "\" and following_id =\"" + other_user_id + "\"");
		try {
			if(follow.rs.next()) { // 있으면
				button_1.setIcon(new ImageIcon(See_user_board.class.getResource("/image/Unfollow.png")));
			}
			else { // 없으면
				button_1.setIcon(new ImageIcon(See_user_board.class.getResource("/image/Follow.png")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		button_1.setBorderPainted(false);
		button_1.setBounds(246, 63, 80, 30);
		user_info_panel.add(button_1);
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DB follow = new DB();
				follow.select("select * from follow where follower_id = \"" + User.id + "\" and following_id =\"" + other_user_id + "\"");
				
				try {
					if(follow.rs.next()) { // 있으면 
						String sql = "DELETE FROM follow WHERE follower_id = '" + User.id + "' AND following_id = '" + other_user_id + "'";
						System.out.println(sql);
						follow.delete(sql);
						See_user_board new_window = new See_user_board(other_user_id);
						new_window.frame.setVisible(true);
						frame.setVisible(false);
					}
					else { // 없으면
						String sql = "insert into follow (follower_id, following_id) values ( \'" + User.id + "\', \'" +  other_user_id + "\' )";
						System.out.println(sql);
						follow.insert(sql);
						See_user_board new_window = new See_user_board(other_user_id);
						new_window.frame.setVisible(true);
						frame.setVisible(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JPanel post_panel = new JPanel();
		post_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255)));
		post_panel.setBackground(Color.WHITE);
		post_panel.setBounds(30, 169, 338, 497);
		frame.getContentPane().add(post_panel);
		post_panel.setLayout(null);
		
		// 몇개 불러올 위한 쿼리
				String sql = "SELECT * FROM post WHERE writter_id = '" + other_user_id + "' ORDER BY time DESC LIMIT 0,3";
				connect = new DB();
				connect.select(sql);
				
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
								See_Comment new_window = new See_Comment(idx, other_user_id);
								new_window.frame.setVisible(true);
								frame.setVisible(false);
							}
						});
				
						
						// 커멘트 갯수
						JLabel comment_num_label = new JLabel("" + count_comment);
						comment_num_label.setHorizontalAlignment(SwingConstants.CENTER);
						comment_num_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
						comment_num_label.setBounds(35, 82, 25, 25);
						content_panel.add(comment_num_label);
						
						// 좋아요 갯수
						sql = "SELECT count_like FROM post WHERE idx = " + connect.rs.getInt("idx");
						DB connect1 = new DB();
						connect1.select(sql);
						connect1.rs.next();
								
						JLabel like_num_label = new JLabel("" + connect1.rs.getInt("count_like"));
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
								See_Comment new_window = new See_Comment(idx, other_user_id);
								new_window.frame.setVisible(true);
								frame.setVisible(false);
							}
						});
				
						
						// 커멘트 갯수
						JLabel comment_num_label2 = new JLabel("" + count_comment);
						comment_num_label2.setHorizontalAlignment(SwingConstants.CENTER);
						comment_num_label2.setFont(new Font("나눔고딕", Font.BOLD, 12));
						comment_num_label2.setBounds(35, 82, 25, 25);
						content_panel2.add(comment_num_label2);
						
						// 좋아요 갯수
						sql = "SELECT count_like FROM post WHERE idx = " + connect.rs.getInt("idx");
						DB connect1 = new DB();
						connect1.select(sql);
						connect1.rs.next();
								
						JLabel like_num_label2 = new JLabel("" + connect1.rs.getInt("count_like"));
						like_num_label2.setHorizontalAlignment(SwingConstants.CENTER);
						like_num_label2.setFont(new Font("나눔고딕", Font.BOLD, 12));
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
								See_Comment new_window = new See_Comment(idx, other_user_id);
								new_window.frame.setVisible(true);
								frame.setVisible(false);
							}
						});
				
						
						// 커멘트 갯수
						JLabel comment_num_label3 = new JLabel("" + count_comment);
						comment_num_label3.setHorizontalAlignment(SwingConstants.CENTER);
						comment_num_label3.setFont(new Font("나눔고딕", Font.BOLD, 12));
						comment_num_label3.setBounds(35, 82, 25, 25);
						content_panel3.add(comment_num_label3);
						
						// 좋아요 갯수
						sql = "SELECT count_like FROM post WHERE idx = " + connect.rs.getInt("idx");
						DB connect1 = new DB();
						connect1.select(sql);
						connect1.rs.next();
								
						JLabel like_num_label3 = new JLabel("" + connect1.rs.getInt("count_like"));
						like_num_label3.setHorizontalAlignment(SwingConstants.CENTER);
						like_num_label3.setFont(new Font("나눔고딕", Font.BOLD, 12));
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

		
		frame.setVisible(true);
	}

}
