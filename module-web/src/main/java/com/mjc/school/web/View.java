package com.mjc.school.web;

import com.mjc.school.repository.FilePathUtils;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.repository.DataSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class View {
		private final Scanner scanner = new Scanner(System.in);
		private final DataSource dataSource = new DataSource(FilePathUtils.NEWS_TXT);
		public int mainMenu() {
				System.out.println("""
						Enter the number of operation:
						1 - Get all news.
						2 - Get news by id.
						3 - Create news.
						4 - Update news.
						5 - Remove news by id.
						0 - Exit.""");
				return scanner.nextInt();
		}
		public void allNewsView(List<NewsModelDto> newsModelDtoList) {
				System.out.println("List of all news");
				newsModelDtoList.forEach(System.out::println);
		}

		public Long newsByIdView() {
				System.out.println("Please enter news id:");
				return scanner.nextLong();
		}
		public NewsModelDto createNewsView() {
				scanner.nextLine();
				System.out.println("Please enter title:");
				String title = scanner.nextLine();
				System.out.println("Please enter content:");
				String content = scanner.nextLine();
				System.out.println("Please enter Author Id:");
				Long authorId = scanner.nextLong();
				NewsModelDto newsModelDTO = new NewsModelDto();
				newsModelDTO.setId((long) dataSource.parseNewsFromFile().size() + 1);
				newsModelDTO.setTitle(title);
				newsModelDTO.setContent(content);
				newsModelDTO.setCreateDate(LocalDateTime.now());
				newsModelDTO.setLastUpdateDate(LocalDateTime.now());
				newsModelDTO.setAuthorId(authorId);
				return newsModelDTO;
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
		public NewsModelDto updateNewsView(NewsModelDto newsToUpdate) {
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