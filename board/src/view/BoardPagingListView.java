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
		System.out.println("�˻��� �������� �Է����ּ���");
		System.out.print("������ ��ȣ : ");
		pagenum = sc.nextLine();

		if (!Isint.isInt(pagenum)) {
			System.out.println("��ȣ�� �Է����ּ���");
			scan();
		}
	}

	public void select() {

		BoardDAO dao = new BoardDAO();
		HashMap<Integer, BoardVO> map = dao.pagingList(Integer.parseInt(pagenum));

		if (map.size() == 0) {
			System.out.println("��ȸ�� �������� �����ϴ�.");
		}

		System.out.println("�ٽ� ��ȸ�Ͻ÷��� 00���� �����ּ���");
		System.out.println("����ȸ���� �����÷��� 0���� �����ּ���");
		System.out.print("����ȸ �۹�ȣ �Է� : ");

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
				System.out.println("�۹�ȣ�� Ȯ���ϰ� �Է��ϼ���");
				select();
			}

			BoardDetailView bdv = new BoardDetailView();
			bdv.detailselect(catc, map);

		} else {
			System.out.println("��ȣ�� �Է����ּ���");
			select();
		}

	}

}
