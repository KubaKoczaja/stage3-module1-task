package com.mjc.school.web;

import com.mjc.school.repository.DTO.NewsDTO;
import com.mjc.school.repository.NewsParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Scanner;

public class View {
		private final Scanner scanner = new Scanner(System.in);
		public int mainMenu() {
				System.out.println("Enter the number of operation:\n" +
								"1 - Get all news.\n" +
								"2 - Get news by id.\n" +
								"3 - Create news.\n" +
								"4 - Update news.\n" +
								"5 - Remove news by id.\n" +
								"0 - Exit.");
				return scanner.nextInt();
		}
		public void allNewsView(List<NewsDTO> newsDTOList) {
				System.out.println("List of all news");
				newsDTOList.forEach(System.out::println);
		}

		public Long newsByIdView() {
				System.out.println("Please enter news id:");
				return scanner.nextLong();
		}
		public NewsDTO createNewsView() {
				scanner.nextLine();
				System.out.println("Please enter title:");
				String title = scanner.nextLine();
				System.out.println("Please enter content:");
				String content = scanner.nextLine();
				System.out.println("Please enter Author Id:");
				Long authorId = scanner.nextLong();
				NewsDTO newsDTO = new NewsDTO();
				newsDTO.setId((long) NewsParser.parseNewsFromFile().size() + 1);
				newsDTO.setTitle(title);
				newsDTO.setContent(content);
				newsDTO.setCreateDate(LocalDateTime.now());
				newsDTO.setLastUpdateDate(LocalDateTime.now());
				newsDTO.setAuthorId(authorId);
				return newsDTO;
		}
		public Long enterNewsToUpdateView() {
				System.out.println("Please enter news to update:");
				scanner.nextLine();
				return scanner.nextLong();
		}

		public Long enterNewsToRemoveView() {
				System.out.println("Please enter news to remove:");
				scanner.nextLine();
				return scanner.nextLong();
		}
		public NewsDTO updateNewsView(NewsDTO newsToUpdate) {
				scanner.nextLine();
				System.out.println(newsToUpdate);
				System.out.println("Please enter new title:");
				String title = scanner.nextLine();
				System.out.println("Please enter new content:");
				String content = scanner.nextLine();
				newsToUpdate.setTitle(title);
				newsToUpdate.setContent(content);
				newsToUpdate.setLastUpdateDate(LocalDateTime.now());
				return newsToUpdate;
		}
}