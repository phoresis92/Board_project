package main;

import java.util.Scanner;

import view.BoardConditionListView;
import view.BoardInsertView;
import view.BoardPagingListView;
import view.MemberInsertView;

public class BoardMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1.게시물작성");
			System.out.println("2.페이지별 글조회");
			System.out.println("3.조건별 글조회");
			System.out.println("4.회원가입");
			System.out.println("9.종료");
			System.out.print("번호입력 : ");
			String menu = sc.nextLine();

			if (menu.equals("9")) {
				System.exit(0);
			} else if (menu.equals("1")) {
				BoardInsertView biv = new BoardInsertView();
				biv.input();
			} else if (menu.equals("2")) {
				BoardPagingListView bplv = new BoardPagingListView();
				bplv.select();
			} else if (menu.equals("3")) {
				BoardConditionListView bclv = new BoardConditionListView();
				bclv.serch();
			} else if (menu.equals("4")) {
				MemberInsertView miv = new MemberInsertView();
				miv.memberinsert();
			}

			else {
				System.out.println("숫자를 입력해 주세요!!!");
			}

		} // while end
	}// main end
}
