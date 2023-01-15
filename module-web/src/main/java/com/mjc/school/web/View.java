package com.mjc.school.web;

import com.mjc.school.controller.NewsModelController;
import com.mjc.school.controller.implementation.NewsModelControllerImpl;
import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.FilePathUtils;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;

import java.time.LocalDateTime;
import java.util.Scanner;

public class View {
		private final Scanner scanner = new Scanner(System.in);
		private final DataSource dataSource = new DataSource(FilePathUtils.NEWS_TXT);
		private final NewsModelController newsModelController = new NewsModelControllerImpl();
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
		public void start() {
				int menuOption;
				do {
						menuOption = mainMenu();
						switch (menuOption) {
								case 1 -> allNewsView();
								case 2 -> newsByIdView();
								case 3 -> createNewsView();
								case 4 -> updateNewsView();
								case 5 -> deleteByIdView();
								case 0 -> exitView();
								default -> {
										System.out.println("Please try again.");
										start();
								}
						}
				} while(menuOption != 0);
		}

		public void allNewsView() {
				System.out.println("List of all news");
				newsModelController.readAllNewsRequest().forEach(System.out::println);
		}

		public void newsByIdView() {
				System.out.println("Please enter news id:");
				try {
						System.out.println(newsModelController.readByIdRequest(scanner.nextLong()));
				} catch (NoSuchNewsException e) {
						System.out.println(e.getMessage());
						start();
				}
		}
		public void createNewsView() {
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
				try {
						newsModelController.createNewsRequest(newsModelDTO);
				} catch (InvalidNewsContentException e) {
						System.out.println(e.getMessage());
						start();
				}
		}
		public void deleteByIdView() {
				scanner.nextLine();
				System.out.println("Please enter news to remove:");
				try {
						newsModelController.deleteByIdRequest(scanner.nextLong());
				} catch (NoSuchNewsException e) {
						System.out.println(e.getMessage());
						start();
				}
		}
		public void updateNewsView() {
				scanner.nextLine();
				System.out.println("Please enter news to update:");
				Long id = scanner.nextLong();
				System.out.println("Please enter new title:");
				scanner.nextLine();
				String title = scanner.nextLine();
				System.out.println("Please enter new content:");
				String content = scanner.nextLine();
				NewsModelDto newsModelDtoToUpdate = new NewsModelDto();
				newsModelDtoToUpdate.setId(id);
				newsModelDtoToUpdate.setTitle(title);
				newsModelDtoToUpdate.setContent(content);
				newsModelDtoToUpdate.setLastUpdateDate(LocalDateTime.now());
				try {
						newsModelController.updateNewsRequest(newsModelDtoToUpdate);
				} catch (NoSuchNewsException | InvalidNewsContentException e) {
						System.out.println(e.getMessage());
					  start();
				}
		}
		public void exitView() {
				System.out.println("Exit");
		}
}