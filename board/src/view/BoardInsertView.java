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

		System.out.print("������ �Է����ּ��� : ");
		String title = sc.nextLine();
		System.out.println("������ �Է����ּ��� : ");
		String contents = sc.nextLine();

		int password = pass();

		vo = new BoardVO(0, title, contents, writer, "sysdate", 0, password);
		// System.out.println(vo);
	}

	public void input() {

		int result = dao.insertBoard(vo);

		if (result == 1) {
			System.out.println("�۾��⸦ �Ϸ��Ͽ����ϴ�.");
		} else {
			System.out.println("�۾��� ������ �߻��Ͽ����ϴ�.");
		}

	}

	protected int pass() {
		System.out.print("��ȣ�� �Է����ּ��� : ");
		String pass = sc.nextLine();
		int password = 0;
		if (Isint.isInt(pass)) {
			password = Integer.parseInt(pass);
		} else {
			System.out.println("��ȣ�� �Է����ּ���!");
			password = pass();
		}
		return password;
	}

}// class end
