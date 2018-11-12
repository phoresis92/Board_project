package view;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardPagingListView {

	String pagenum = "";
	String detailnum = "";

	public BoardPagingListView() {

		scan();

	}

	public void scan() {
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 페이지를 입력해주세요");
		System.out.print("페이지 번호 : ");
		pagenum = sc.nextLine();

		if (!Isint.isInt(pagenum)) {
			System.out.println("번호를 입력해주세요");
			scan();
		}
	}

	public void select() {

		BoardDAO dao = new BoardDAO();
		HashMap<Integer, BoardVO> map = dao.pagingList(Integer.parseInt(pagenum));

		if (map.size() == 0) {
			System.out.println("조회할 페이지가 없습니다.");
		}

		System.out.println("다시 조회하시려면 00번을 눌러주세요");
		System.out.println("상세조회하지 않으시려면 0번을 눌러주세요");
		System.out.print("상세조회 글번호 입력 : ");

		Scanner sc = new Scanner(System.in);
		detailnum = sc.nextLine();
		int catc = 0;
		if (detailnum.equals("00")) {

			scan();
			select();

		} else if (detailnum.equals("0")) {
			return;
		} else if (Isint.isInt(detailnum)) {

			Set<Integer> set = map.keySet();
			for (Integer in : set) {
				if (detailnum.equals(in.toString())) {
					catc = in;
				}
			}

			if (catc == 0) {
				System.out.println("글번호를 확인하고 입력하세요");
				select();
			}

			BoardDetailView bdv = new BoardDetailView();
			bdv.detailselect(catc, map);

		} else {
			System.out.println("번호를 입력해주세요");
			select();
		}

	}

}
