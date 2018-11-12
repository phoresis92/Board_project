package view;

import java.util.HashMap;
import java.util.Scanner;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardDetailView {

	Scanner sc = new Scanner(System.in);

	public void detailselect(int detail, HashMap<Integer, BoardVO> map) {

		BoardVO vo = map.get(detail);
		vo.setViewcount(vo.getViewcount() + 1);
		System.out.println("-----------------------------");
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("-----------------------------");
		System.out.println("내용 : " + vo.getContents());
		System.out.println("작성자 : " + vo.getWriter());
		System.out.println("-----------------------------");
		System.out.println("작성시간 : " + vo.getTime());
		System.out.println("조회수 : " + vo.getViewcount());
		System.out.println("-----------------------------");

		BoardDAO dao = new BoardDAO();
		dao.viewcount(vo);

		updelete(vo);

	}

	void updelete(BoardVO vo) {
		BoardDAO dao = new BoardDAO();
		System.out.println("1. 글 수정");
		System.out.println("2. 글 삭제");
		System.out.println("3. 첫 화면");
		System.out.print("번호입력 : ");

		String num = sc.nextLine();

		if (num.equals("3")) {
			return;
		} else if (num.equals("1") && equalpass(vo)) {
			System.out.print("변경 제목 : ");
			String title = sc.nextLine();

			System.out.println("변경 내용 : ");
			String contents = sc.nextLine();

			String writer = dao.isUserID();

			if (dao.updateBoard(vo, title, contents, writer) == 1) {
				System.out.println("변경처리 되었습니다.");
			}

		} else if (num.equals("2") && equalpass(vo)) {
			if (dao.deleteBoard(vo) == 1) {
				System.out.println("게시물 삭제 완료되었습니다.");
			}

		} else {
			System.out.println("숫자를 다시 입력하세요");
			updelete(vo);
		}
	}

	boolean equalpass(BoardVO vo) {
		System.out.print("비밀번호를 입력해주세요 : ");
		String inputpass = sc.nextLine();

		if (!Isint.isInt(inputpass)) {
			return false;
		} else if (Integer.parseInt(inputpass) == vo.getPassword()) {
			return true;
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");

		}
		return false;
	}

}
