package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.MemberVO;

public class MemberDAO {

	public int memberinsert(MemberVO vo) {

		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			// System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "insert into member values " + " ( ?, ?, ?, ?, ?, sysdate, ?, ?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, vo.getUserid());
			st.setString(2, vo.getPassword());
			st.setString(3, vo.getName());
			st.setString(4, vo.getAddress());
			st.setString(5, vo.getGender());
			st.setString(6, vo.getPhone());
			st.setString(7, vo.getEmail());
			result = st.executeUpdate();

			con.commit();

			st.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {

			try {
				con.close(); // 상황에 따른 문제 / 자바의 문제가 아니다
			} catch (SQLException e) {
			}
			// System.out.println("연결해제성공");
		}
		return result;

	}

}
