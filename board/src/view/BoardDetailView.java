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
		System.out.println("���� : " + vo.getTitle());
		System.out.println("-----------------------------");
		System.out.println("���� : " + vo.getContents());
		System.out.println("�ۼ��� : " + vo.getWriter());
		System.out.println("-----------------------------");
		System.out.println("�ۼ��ð� : " + vo.getTime());
		System.out.println("��ȸ�� : " + vo.getViewcount());
		System.out.println("-----------------------------");

		BoardDAO dao = new BoardDAO();
		dao.viewcount(vo);

		updelete(vo);

	}

	void updelete(BoardVO vo) {
		BoardDAO dao = new BoardDAO();
		System.out.println("1. �� ����");
		System.out.println("2. �� ����");
		System.out.println("3. ù ȭ��");
		System.out.print("��ȣ�Է� : ");

		String num = sc.nextLine();

		if (num.equals("3")) {
			return;
		} else if (num.equals("1") && equalpass(vo)) {
			System.out.print("���� ���� : ");
			String title = sc.nextLine();

			System.out.println("���� ���� : ");
			String contents = sc.nextLine();

			String writer = dao.isUserID();

			if (dao.updateBoard(vo, title, contents, writer) == 1) {
				System.out.println("����ó�� �Ǿ����ϴ�.");
			}

		} else if (num.equals("2") && equalpass(vo)) {
			if (dao.deleteBoard(vo) == 1) {
				System.out.println("�Խù� ���� �Ϸ�Ǿ����ϴ�.");
			}

		} else {
			System.out.println("���ڸ� �ٽ� �Է��ϼ���");
			updelete(vo);
		}
	}

	boolean equalpass(BoardVO vo) {
		System.out.print("��й�ȣ�� �Է����ּ��� : ");
		String inputpass = sc.nextLine();

		if (!Isint.isInt(inputpass)) {
			return false;
		} else if (Integer.parseInt(inputpass) == vo.getPassword()) {
			return true;
		} else {
			System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");

		}
		return false;
	}

}
