package com.mjc.school;

import com.mjc.school.repository.DTO.NewsDTO;
import com.mjc.school.repository.NewsGenerator;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.web.View;

import java.util.List;

public class Controller {
		private final View view = new View();
		private final NewsService newsService = new NewsService();
			public void start() {
					NewsGenerator.generateNews();
					int menuOption;
					do {
							menuOption = view.mainMenu();
							switch (menuOption) {
									case 1 -> handleAllNewsRequest();
									case 2 -> handleNewsById();
									case 3 -> handleCreateNewsRequest();
									case 4 -> handleUpdateNewsRequest();
									case 5 -> handleRemoveNewsById();
									case 0 -> handleExitRequest();
									default -> {
											System.out.println("Please try again.");
											start();
									}
							}
					} while(menuOption != 0);
			}

			public void handleAllNewsRequest() {
					List<NewsDTO> newsList = newsService.getAllNews();
					view.allNewsView(newsList);
			}

			public void handleNewsById() {
					try {
							System.out.println(newsService.getNewsById(view.newsByIdView()));
					} catch (NoSuchNewsException e) {
							System.out.println(e.getMessage());
							start();
					}
			}

			public void handleCreateNewsRequest() {
					NewsDTO newsDTOInput = view.createNewsView();
					try {
							newsService.addNewNews(newsDTOInput);
					} catch (InvalidNewsContentException e) {
							System.out.println(e.getMessage());
							start();
					}
			}
			public void handleUpdateNewsRequest() {
					NewsDTO newsDTOToUpdate = null;
					try {
							newsDTOToUpdate = newsService.getNewsById(view.enterNewsToUpdateView());
					} catch (NoSuchNewsException e) {
							System.out.println(e.getMessage());
							start();
					}
					NewsDTO updatedNewsDTO = view.updateNewsView(newsDTOToUpdate);
					try {
							newsService.updateNews(updatedNewsDTO);
					} catch (InvalidNewsContentException e) {
							System.out.println(e.getMessage());
							start();
					}
			}
			public void handleRemoveNewsById() {
					try {
							newsService.removeNewsById(view.enterNewsToRemoveView());
					} catch (NoSuchNewsException e) {
							System.out.println(e.getMessage());
							start();
					}
			}
			public void handleExitRequest() {
					System.out.println("Exit");
			}
}
