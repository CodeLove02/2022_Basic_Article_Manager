package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;

	App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		MemberController MemberController = new MemberController(members, sc);
		ArticleController ArticleController = new ArticleController(articles, sc);
		
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

	private void makeTestData() {
		System.out.println("테스트를 위한 게시물을 생성합니다");
		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 33));
	}

}
