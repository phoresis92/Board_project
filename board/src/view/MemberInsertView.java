package view;

import java.util.Scanner;
import java.util.regex.Pattern;

import dao.MemberDAO;
import vo.MemberVO;

public class MemberInsertView {

	Scanner sc = new Scanner(System.in);

	public void memberinsert() {

		System.out.println("회원가입 메뉴입니다.");
		System.out.print("아이디 입력 : ");
		String userid = sc.nextLine();
		while (userid.equals("")) {
			System.out.println("입력값이 없습니다. 다시 입력해주세요!");
			userid = sc.nextLine();
		}
		System.out.print("비밀번호 입력 : ");
		String password = sc.nextLine();
		while (password.equals("")) {
			System.out.println("입력값이 없습니다. 다시 입력해주세요!");
			password = sc.nextLine();
		}
		System.out.print("이름 입력 : ");
		String name = sc.nextLine();
		System.out.print("주소 입력 : ");
		String address = sc.nextLine();
		System.out.print("성별 입력(F또는M으로 입력해주세요) : ");
		String gender = sc.nextLine().toUpperCase();
		String reg = "(F|M)";
		/*
		 * while (!gender.equals("F") || !gender.equals("M")) {
		 * System.out.println("F또는M으로 입력해주세요!!!"); gender = sc.nextLine().toUpperCase();
		 * if (gender.equals("F") || gender.equals("M")) { break; } }
		 */

		while (!Pattern.matches(reg, gender)) {
			System.out.println("F또는M으로 입력해주세요!!!");
			gender = sc.nextLine().toUpperCase();
		}

		// System.out.println(Pattern.matches(reg, gender));

		String indate = "sysdate";
		System.out.print("연락처 입력 : ");
		String phone = sc.nextLine();
		System.out.print("이메일 입력 : ");
		String email = sc.nextLine();

		MemberVO vo = new MemberVO(userid, password, name, address, gender, indate, phone, email);

		MemberDAO dao = new MemberDAO();
		int result = dao.memberinsert(vo);
		if (result == 1) {
			System.out.println("회원가입 완료되었습니다.");
		}

	}

}
