package view;

import java.util.Scanner;
import java.util.regex.Pattern;

import dao.MemberDAO;
import vo.MemberVO;

public class MemberInsertView {

	Scanner sc = new Scanner(System.in);

	public void memberinsert() {

		System.out.println("ȸ������ �޴��Դϴ�.");
		System.out.print("���̵� �Է� : ");
		String userid = sc.nextLine();
		while (userid.equals("")) {
			System.out.println("�Է°��� �����ϴ�. �ٽ� �Է����ּ���!");
			userid = sc.nextLine();
		}
		System.out.print("��й�ȣ �Է� : ");
		String password = sc.nextLine();
		while (password.equals("")) {
			System.out.println("�Է°��� �����ϴ�. �ٽ� �Է����ּ���!");
			password = sc.nextLine();
		}
		System.out.print("�̸� �Է� : ");
		String name = sc.nextLine();
		System.out.print("�ּ� �Է� : ");
		String address = sc.nextLine();
		System.out.print("���� �Է�(F�Ǵ�M���� �Է����ּ���) : ");
		String gender = sc.nextLine().toUpperCase();
		String reg = "(F|M)";
		/*
		 * while (!gender.equals("F") || !gender.equals("M")) {
		 * System.out.println("F�Ǵ�M���� �Է����ּ���!!!"); gender = sc.nextLine().toUpperCase();
		 * if (gender.equals("F") || gender.equals("M")) { break; } }
		 */

		while (!Pattern.matches(reg, gender)) {
			System.out.println("F�Ǵ�M���� �Է����ּ���!!!");
			gender = sc.nextLine().toUpperCase();
		}

		// System.out.println(Pattern.matches(reg, gender));

		String indate = "sysdate";
		System.out.print("����ó �Է� : ");
		String phone = sc.nextLine();
		System.out.print("�̸��� �Է� : ");
		String email = sc.nextLine();

		MemberVO vo = new MemberVO(userid, password, name, address, gender, indate, phone, email);

		MemberDAO dao = new MemberDAO();
		int result = dao.memberinsert(vo);
		if (result == 1) {
			System.out.println("ȸ������ �Ϸ�Ǿ����ϴ�.");
		}

	}

}
