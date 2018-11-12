package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import main.BoardMain;
import view.BoardConditionListView;
import vo.BoardVO;

public class BoardDAO {

	public int insertBoard(BoardVO vo) {

		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			// System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "insert into Board values " + " ((select max(seq) from board)+1, ?,?,?,sysdate,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, vo.getTitle());
			st.setString(2, vo.getContents());
			st.setString(3, vo.getWriter());
			st.setInt(4, vo.getViewcount());
			st.setInt(5, vo.getPassword());
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
	}// method end

	public String isUserID() {
		String writer = "";
		int exist = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			// System.out.println("연결성공");

			con.setAutoCommit(false);

			Scanner sc = new Scanner(System.in);
			System.out.print("작성자를 입력해주세요 : ");
			writer = sc.nextLine();

			String sql = "select count(*) from member where userid = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, writer);
			ResultSet result = st.executeQuery();

			while (result.next()) {
				exist = result.getInt("count(*)");
			}

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

		} finally {

			try {
				con.close(); // 상황에 따른 문제 / 자바의 문제가 아니다
			} catch (SQLException e) {
			}
			// System.out.println("연결해제성공");
		}

		if (exist == 0) {
			System.out.println("사용하시는 아이디가 없습니다.");
			isUserID();
			return "";
		} else {

			return writer;
		}
	}

	public HashMap<Integer, BoardVO> pagingList(int pagenum) {

		HashMap<Integer, BoardVO> map = null;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			// System.out.println("연결성공");

			con.setAutoCommit(false);

			int split = 10;
			int start = (pagenum - 1) * split + 1;
			int end = pagenum * split;

			String sql = "select *" + " from(select rownum r , intable.*" + " from(select * "
					+ " from board order by time desc) intable)" + " where r>= ? and r<= ? order by seq desc";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();

			map = new HashMap<Integer, BoardVO>();
			BoardVO vo = null;

			while (rs.next()) {

				int seq = rs.getInt("seq");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String writer = rs.getString("writer");
				String time = rs.getString("time");
				int viewcount = rs.getInt("viewcount");
				int password = rs.getInt("password");

				vo = new BoardVO(seq, title, contents, writer, time, viewcount, password);

				map.put(seq, vo);

				System.out.println(seq + ") " + title + " " + writer + " " + time + " " + viewcount);

			}

			System.out.println("현제페이지 : " + pagenum);

			con.commit();

			rs.close();
			ps.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {

			try {
				con.close(); // 상황에 따른 문제 / 자바의 문제가 아니다
			} catch (SQLException e) {
			}
			// System.out.println("연결해제성공");
		}
		return map;

	}// pagingList end

	public void viewcount(BoardVO vo) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			// System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "update board" + " set viewcount = viewcount+1" + " where seq = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, vo.getSeq());
			result = st.executeUpdate();

			// System.out.println(result+"viewcount added");
			con.commit();

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
			// 예외발생 - try중단 - catch이동
			// 5. 연결해제, 파일 close 소켓 close
			try {
				con.close(); // 상황에 따른 문제 / 자바의 문제가 아니다
			} catch (SQLException e) {
			}
			// System.out.println("연결해제성공");
		}

	}// viewcount method

	public HashMap<Integer, BoardVO> conditionSerch(String colum, String value) {

		HashMap<Integer, BoardVO> map = null;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			// System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "select * from Board where " + colum + " = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, value);
			ResultSet rs = st.executeQuery();

			map = new HashMap<Integer, BoardVO>();

			while (rs.next()) {
				int seq = rs.getInt("seq");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String writer = rs.getString("writer");
				String time = rs.getString("time");
				int viewcount = rs.getInt("viewcount");
				int password = rs.getInt("password");

				BoardVO vo = new BoardVO(seq, title, contents, writer, time, viewcount, password);

				map.put(seq, vo);

				System.out.println(seq + ") " + title + " " + writer + " " + time + " " + viewcount);

			}

			if (map.size() == 0) {
				System.out.println("찾는 게시물이 존재하지 않습니다.");
			}

			con.commit();

			st.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			System.out.println("컬럼값이 잘못 입력되었습니다.");
			new BoardConditionListView().serch();

		} finally {

			try {
				con.close(); // 상황에 따른 문제 / 자바의 문제가 아니다
			} catch (SQLException e) {
			}
			// System.out.println("연결해제성공");
		}
		return map;

	}

	public int updateBoard(BoardVO vo, String title, String contents, String writer) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			// System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "update board" + " set title = ? , contents = ? , writer = ?" + " where seq = " + vo.getSeq();

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, contents);
			st.setString(3, writer);
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

	public int deleteBoard(BoardVO vo) {

		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			// System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "delete board where seq = " + vo.getSeq();

			Statement st = con.createStatement();

			result = st.executeUpdate(sql);

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
