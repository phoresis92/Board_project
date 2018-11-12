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
		System.out.print("�÷��� :");
		colum = sc.nextLine();
		System.out.print("���ǰ� : ");
		value = sc.nextLine();

		if (colum.equals("password")) {
			System.out.println("��й�ȣ�� �˻��Ͻ� �� �����ϴ�.");
			scan();
		}

	}

	public void serch() {
		BoardDAO dao = new BoardDAO();
		HashMap<Integer, BoardVO> map = dao.conditionSerch(colum, value);

		System.out.println("�ٽ� ��ȸ�Ͻ÷��� 00���� �����ּ���");
		System.out.println("����ȸ���� �����÷���  0���� �����ּ���");
		System.out.print("����ȸ �۹�ȣ �Է� : ");

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
