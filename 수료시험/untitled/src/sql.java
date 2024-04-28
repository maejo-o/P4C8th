import java.util.*;
import java.sql.*;

public class sql {

	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd ="0000";
			con = DriverManager.getConnection(url,user,passwd);
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Scanner s = new Scanner(System.in);
		Statement stmt = null;
		Statement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		PreparedStatement pstm = null;
		
		
		try {
			
			while(true) {
				
				stmt = con.createStatement();
				
				int op1;
				
				String id = null;
				String pw = null;
				String birthday = null;
				
				System.out.println("===============================================");
				System.out.println("                Welcome Twitter                ");
				System.out.println("===============================================");
				System.out.println(" ");
				
				System.out.println("===============================================");
				System.out.println("|| log in   : 0 ||"
							   + "\n|| sign up  : 1 || "
							   + "\n||Delete ID : 2 ||");
				System.out.println("===============================================");
				System.out.println(" ");
				op1 = s.nextInt();
				
				//ȸ������
				if(op1 == 1) {
					
					String s1 = null;
					System.out.println(" ");
					System.out.println("===============================================");
					System.out.println("                Input userid                   ");
					System.out.println("===============================================");
					System.out.println(" ");
					id = s.next();
					
					
					stmt = con.createStatement();
					
					String s2 = "select id from user where id = \"" + id + "\"";
					rs = stmt.executeQuery(s2);
					
					if(rs.next()) {
						System.out.println(" ");
						System.out.println("===============================================");
						System.out.println("  User name already exists. Please try again!  ");
						System.out.println("===============================================");
						System.out.println(" ");
					}
					else {
						System.out.println(" ");
						System.out.println("===============================================");
						System.out.println("ID : "+ id + " is available!.         ");
						System.out.println(" ");
						System.out.println("     Input Password / birthday(0000-00-00)     ");
						System.out.println("===============================================");
						System.out.println(" ");
						pw = s.next();
						birthday = s.next();
						
						s1 = "insert into user values(\"" + id + "\", \"" + pw + "\", \"" + birthday + "\",1,0,0)";
						
						pstm = con.prepareStatement(s1);
						pstm.executeUpdate(s1);
						System.out.println(" ");
						System.out.println("===============================================");
						System.out.println("              Sign up completed!               ");
						System.out.println("===============================================");
						System.out.println(" ");
					}
				}
				//�α���
				else if(op1 == 0) {
					
					System.out.println(" ");
					System.out.println("===============================================");
					System.out.println("            Input ID / Password                ");
					System.out.println("===============================================");
					System.out.println(" ");
					id = s.next();
					pw = s.next();
						
					stmt = con.createStatement();
					String s1 = "select id from user where id = \"" + id + "\" and pw = \"" + pw +"\"";
					rs = stmt.executeQuery(s1);
						
					if(rs.next()) {
						System.out.println(" ");
						System.out.println("===============================================");	
						System.out.println("                Loggen id!!                    ");
						System.out.println("===============================================");
						System.out.println(" ");
						
						//�α��� ����
						int op2 = 0;
						while(op2 != -1) {
							System.out.println(" ");
							System.out.println("===============================================");	
							System.out.println("||Show post        : 0||\n"
											 + "||Write post       : 1||\n"
											 + "||See my followers : 2||\n"
											 + "||see my follwing  : 3||\n"
											 + "||follow someone   : 4||\n"
											 + "||Loign page       :-1||");
							System.out.println("===============================================");
							System.out.println(" ");
							op2 = s.nextInt();
							
							//�ȷο� ����
							if (op2 == 2) {
								stmt = con.createStatement();
								
								String s2 = "select follower_id from follower where user_user_id = \"" + id + "\"";
								rs = stmt.executeQuery(s2);
								
								System.out.println("===============================================");
								System.out.println("                  Follower                     ");
								while (rs.next()) {
									
									String result = rs.getString(1);
									
									System.out.println(result);
								}
								System.out.println("===============================================");
								
							}
							
							//�ķ��� ����
							else if (op2 == 3) {
								stmt = con.createStatement();
								
								String s2 = "select user_user_id from following where following_id = \"" + id + "\"";
								rs = stmt.executeQuery(s2);
								
								System.out.println("===============================================");
								System.out.println("                  Following                     ");
								while (rs.next()) {
									
									String result = rs.getString(1);
									
									System.out.println(result);
								}
								System.out.println("===============================================");
								
							}
							
							
							//�ȷο� �ϱ�
							// a�� b�� �ȿ��� �ϸ�
							// follower �� follower_id = b, user_user_id = a
							// following �� follwing_id = a, user_user_id = b
							else if (op2 == 4) {
								
								
								
								String u_id = null;
								System.out.println("===============================================");
								System.out.println("           Input user ID to follow             ");
								System.out.println("===============================================");
								u_id = s.next();
								
								if(u_id.equals(id)) {
									System.out.println("Can't follow yourself");
									
								}
								else {
									stmt = con.createStatement();
									
									String s2 = "select follower_id from follower where follower_id = \"" + u_id + "\"";
									rs = stmt.executeQuery(s2);
									
									if(rs.next() ) {
										System.out.println("Already follow the user. Please try again!");
									}
									else {
										
										
										String s3 = "insert into follower values(null,\""+id+"\", \""+u_id+"\")";
										String s4 = "insert into following values(null,\""+u_id+"\", \""+id+"\")";
										
										pstm = con.prepareStatement(s3);
										pstm.executeUpdate();
										
										pstm = con.prepareStatement(s4);
										pstm.executeUpdate();
										
										System.out.println("===============================================");
										System.out.println("                 Follow Done                   ");
										System.out.println("===============================================");
							
									}
				
								}
								
							}
							
							
							//����Ʈ ����
							else if (op2 == 1) {
								
								
								String text = null;
								System.out.println("===============================================");
								System.out.println("                  Write Post                   ");
								System.out.println("===============================================");
								text = s.next();
								
								stmt = con.createStatement();
								s1 = "insert into posts values(null,null,\"" + id + "\", \""+ text + "\",0,0)";
								
									
								pstm = con.prepareStatement(s1);
								pstm.executeUpdate();
								
								System.out.println("===============================================");
								System.out.println("                   Post Done                   ");
								System.out.println("===============================================");
								
							}
							
							//����Ʈ �����ֱ�
							else if (op2 == 0) {
								
								System.out.println("===============================================");
								System.out.println("                   Show Post                   ");
								System.out.println("===============================================");
		
								stmt = con.createStatement();
								String sql = "select idx,writter_id,content from posts";
								rs = stmt.executeQuery(sql);
								
								while (rs.next()) {
									String idx = rs.getString(1);
									String writter = rs.getString(2);
									String content = rs.getString(3);
									System.out.println("===============================================");
									System.out.println("Post Number : "+ idx + " /Writter :  " + writter);
									System.out.println("Content : \n" + content);
									System.out.println("===============================================");
								}
								
								//����Ʈ ���� �� �ڷΰ��� �����(���߿� �����ϱ⵵ �־����)
								int op3 = 0;
								while (op3 != -1) {
									System.out.println(" ");
									System.out.println("===============================================");	
									System.out.println("||Select post        : 1||\n"
													 + "||Before page        :-1||");
									System.out.println("===============================================");
									System.out.println(" ");
									op3 = s.nextInt();
									
									if (op3 == 1) {
										int post_num;
										System.out.println(" ");
										System.out.println("===============================================");	
										System.out.println("               Select Post Number              ");
										System.out.println("===============================================");
										System.out.println(" ");
										post_num = s.nextInt();
										
										stmt = con.createStatement();
										String s5 = "select idx,writter_id,content, num_of_like from posts where idx = \"" + post_num + "\"";
										rs = stmt.executeQuery(s5);
										
										stmt2 = con.createStatement();
										String s6 = "select count(idx) as count from comment where post_idx = \"" + post_num + "\"";
										rs2 = stmt2.executeQuery(s6);
										
										while(rs.next()) {
											String idx = rs.getString(1);
											String writter = rs.getString(2);
											String content = rs.getString(3);
											Integer num_of_like = rs.getInt(4);
											while(rs2.next()){
												Integer count = rs2.getInt(1);
												System.out.println("===============================================");
												System.out.println("Post Number : "+ idx + " /Writter :  " + writter);
												System.out.println("Content : \n" + content);
												System.out.println("===============================================");
												System.out.println("Like : "+num_of_like+ "          Comment : " +count);
												System.out.println("===============================================");
										
										
										//���ƿ� ������
										int op4 = 0;
										while(op4 != -1) {
											System.out.println(" ");
											System.out.println("===============================================");	
											System.out.println("||Post Like        : 1||\n"
															 + "||Show Comment     : 2||\n"
															 + "||Before page      :-1||");
											System.out.println("===============================================");
											System.out.println(" ");
											op4 = s.nextInt();
											
											if(op4 == 1) {
												stmt = con.createStatement();
												String s7 = "select like_id from post_like where user_user_id = \"" + id + "\" and posts_post_id = \"" + idx + "\"";
												rs = stmt.executeQuery(s7);
												
												if(rs.next()) {
													
													System.out.println("Already liked post");
													
												}
												else {
													
													String s8 = "insert into post_like values(null,\""+id+"\", \""+post_num+"\", \""+writter+"\")";
													String s9 = "update posts set num_of_like = num_of_like + 1 where idx = \""+idx+"\"";
													
													pstm = con.prepareStatement(s8);
													pstm.executeUpdate();
													
													num_of_like = stmt.executeUpdate(s9);
													
													System.out.println(" ");
													System.out.println("===============================================");
													System.out.println("                  Liked done                   ");
													System.out.println("===============================================");
													System.out.println(" ");
													
													System.out.println(" ");
													System.out.println("===============================================");
													System.out.println("Post Number : "+ idx + " /Writter :  " + writter);
													System.out.println("Content : \n" + content);
													System.out.println("===============================================");
													System.out.println("Like : "+num_of_like+ "          Comment : " +count);
													System.out.println("===============================================");
											
													
													
													
												}
											}
										}
											}
											
										}
									}
									
								}
								
								
								
							}
							
							
						}
						
					}
					else {
						System.out.println(" ");
						System.out.println("===============================================");		
						System.out.println("     Wrong id/passwoer. Please log in again.   ");
						System.out.println("===============================================");
						System.out.println(" ");
					}
				
				}
				//���� ����
				else if(op1 == 2) {
					
					System.out.println(" ");
					System.out.println("===============================================");
					System.out.println("            Input ID / Password                ");
					System.out.println("===============================================");
					System.out.println(" ");
					id = s.next();
					pw = s.next();
					
					stmt = con.createStatement();
					String s3 = "select id from user where id = \"" + id + "\" and pw = \"" + pw +"\"";
					rs = stmt.executeQuery(s3);
					
					if(rs.next()) {
						
						
						String s4 = "delete from user where id = \"" + id + "\" and pw = \"" + pw +"\"";
						pstm = con.prepareStatement(s4);
						pstm.executeUpdate();
						
						System.out.println(" ");
						System.out.println("===============================================");
						System.out.println("        ID deletion has been completed.        ");
						System.out.println("===============================================");
						System.out.println(" ");
						
					}
					else {
						
						System.out.println(" ");
						System.out.println("===============================================");		
						System.out.println("        Wrong id/passwoer. Pleasse again.      ");
						System.out.println("===============================================");
						System.out.println(" ");
					}
					
				}
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			if (stmt != null && !stmt.isClosed()) stmt.close();
			if (rs != null && !rs.isClosed()) rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}	
}
	