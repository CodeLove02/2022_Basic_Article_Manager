package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class App {
	private List<Article> articles;

	App() {
		articles = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

//		int lastArticleId = 3;
		while (true) {

			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}
			if (cmd.equals("article write")) {
				int id = articles.size() + 1;
//				lastArticleId = id;
				String regDate = Util.getNowDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);

				System.out.printf("%d글이 생성되었습니다\n", id);

				articles.add(article);

				System.out.printf("%s , %s\n", title, body);
			} else if (cmd.startsWith("article list")) {

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다");
					continue;
				}

				List<Article> forPrintArticles = articles;

				String searchKeyword = cmd.substring("article list".length()).trim();
				System.out.println("검색어" + searchKeyword);

				if (searchKeyword.length() > 0) {
					System.out.println("검색어 : " + searchKeyword);
					forPrintArticles = new ArrayList<>();
					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							forPrintArticles.add(article);
						}
					}
					if (forPrintArticles.size() == 0) {
						System.out.println("검색결과가 없습니다");
						continue;
					}
				}

				System.out.println("번호    |    제목			| 		날짜					| 		조회");
				for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
					Article article = forPrintArticles.get(i);
					System.out.printf("%d    |    %s		|     %s 		|   		%d \n", article.id,
							article.title, article.regDate, article.viewCnt);
				}
			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {

					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				foundArticle.addViewCnt();
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
				System.out.printf("번호 : %s\n", foundArticle.title);
				System.out.printf("번호 : %s\n", foundArticle.body);
				System.out.printf("조회 : %d\n", foundArticle.viewCnt);

			}

			else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split("");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {

					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);

					continue;

				}

				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d번 게시물이 수정되었습니다", id);

			}

			else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split("");
				int id = Integer.parseInt(cmdBits[2]);

				int foundIndex = getArticleIndexById(id);

				if (foundIndex == -1) {

					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				articles.remove(foundIndex);
				System.out.printf("%d번 게시물이 삭제되었습니다\n", id);

			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		String cmd = sc.nextLine();
		System.out.println("입력된 명령어 : " + cmd);
		System.out.println("==프로그램 끝==");
		sc.close();
	}

	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

//	{
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//
//			if (article.id == id) {
//
//				return i;
//			}
//
//		}
//		return -1;
//	}

	private Article getArticleById(int id) {

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}

//	{
//		for (Article article : articles) {
//			if (article.id == id) {
//				return article;
//			}
//		}
//		return null;
//	}

//	{
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//			if (article.id == id) {
//
//				return article;
//			}
//
//		}
//		return null;
//	}

	private void makeTestData() {
		System.out.println("테스트를 위한 게시물을 생성합니다");
		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 33));
	}
}