package view;

import java.util.HashMap;
import java.util.Scanner;

import dao.BoardDAO;
import vo.BoardVO;
import main.BoardMain;

public class BoardConditionListView {

	String colum = "";
	String value = "";

	Scanner sc = new Scanner(System.in);

	public BoardConditionListView() {
		scan();
	}

	public void scan() {
		System.out.println("===seq, title, contents, writer, time, viewcount===");
		System.out.print("컬럼명 :");
		colum = sc.nextLine();
		System.out.print("조건값 : ");
		value = sc.nextLine();

		if (colum.equals("password")) {
			System.out.println("비밀번호로 검색하실 수 없습니다.");
			scan();
		}

	}

	public void serch() {
		BoardDAO dao = new BoardDAO();
		HashMap<Integer, BoardVO> map = dao.conditionSerch(colum, value);

		System.out.println("다시 조회하시려면 00번을 눌러주세요");
		System.out.println("상세조회하지 않으시려면  0번을 눌러주세요");
		System.out.print("상세조회 글번호 입력 : ");

		sc = new Scanner(System.in);
		String detailnum = sc.nextLine();

		if (detailnum.equals("00")) {

			map.clear();

			scan();

			System.out.println(colum);
			System.out.println(value);
			serch();
		} else if (detailnum.equals("0")) {
			return;
		} else {
			int detail = Integer.parseInt(detailnum);
			BoardDetailView bdv = new BoardDetailView();
			bdv.detailselect(detail, map);
		}

	}

}
