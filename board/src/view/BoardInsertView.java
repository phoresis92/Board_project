package view;

import java.util.Scanner;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardInsertView {

	BoardVO vo = null;
	Scanner sc = new Scanner(System.in);
	BoardDAO dao = new BoardDAO();

	public BoardInsertView() {

		String writer = dao.isUserID();

		System.out.print("제목을 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.println("내용을 입력해주세요 : ");
		String contents = sc.nextLine();

		int password = pass();

		vo = new BoardVO(0, title, contents, writer, "sysdate", 0, password);
		// System.out.println(vo);
	}

	public void input() {

		int result = dao.insertBoard(vo);

		if (result == 1) {
			System.out.println("글쓰기를 완료하였습니다.");
		} else {
			System.out.println("글쓰기 오류가 발생하였습니다.");
		}

	}

	protected int pass() {
		System.out.print("암호를 입력해주세요 : ");
		String pass = sc.nextLine();
		int password = 0;
		if (Isint.isInt(pass)) {
			password = Integer.parseInt(pass);
		} else {
			System.out.println("번호를 입력해주세요!");
			password = pass();
		}
		return password;
	}

}// class end
