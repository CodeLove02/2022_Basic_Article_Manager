package com.koreaIT.java.BAM;

import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;

public class App {
	

	public void run() {
		System.out.println("==프로그램 시작==");


		Scanner sc = new Scanner(System.in);

		MemberController MemberController = new MemberController(sc);
		ArticleController ArticleController = new ArticleController(sc);

		ArticleController.makeTestData();
		MemberController.makeTestData();
		
//		int lastArticleId = 3;
		while (true) {

			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			else if (cmd.equals("exit")) {
				break;
			}
			
			String[] cmdBits = cmd.split(" ");
			
			if(cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요");
				continue;
			}
			
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			Controller controller = null;
	
			if(controllerName.equals("article")){
				controller = ArticleController; 
			}
			else if(controllerName.equals("member")){
				controller = MemberController; 
			}
			else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
			
			controller.doAction(cmd,methodName);
			
//			else if (cmd.equals("member join")) {
//				MemberController.doJoin();
//			}
//			
//			else if (cmd.equals("article write")) {
//				ArticleController.doWrite();
//			}
//			
//			else if (cmd.startsWith("article list")) {
//				ArticleController.showList(cmd);
//				
//				}
//			 
//			else if (cmd.startsWith("article detail ")) {
//				ArticleController.showDetail(cmd);
//			}
//
//			else if (cmd.startsWith("article modify ")) {
//				ArticleController.doModify(cmd);
//
//			}
//			
//			else if (cmd.startsWith("article delete ")) {
//				ArticleController.doDelete(cmd);
//			}
//
//
//			else {
//				System.out.println("존재하지 않는 명령어입니다.");
//			}
//		}


			
	}

		System.out.println("==프로그램 끝==");
		sc.close();
	}

	

}
