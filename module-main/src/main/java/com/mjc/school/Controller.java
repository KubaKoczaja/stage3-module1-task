package com.mjc.school;

import com.mjc.school.repository.NewsGenerator;
import com.mjc.school.repository.model.dto.NewsModelDTO;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.web.View;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class Controller {
		private final View view = new View();
		private final NewsService newsService = new NewsService();
		private final NewsGenerator newsGenerator = new NewsGenerator();
			public void start() {
					newsGenerator.generateNews();
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
					List<NewsModelDTO> newsList = newsService.getAllNews();
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
					NewsModelDTO newsModelDTOInput = view.createNewsView();
					try {
							newsService.addNewNews(newsModelDTOInput);
					} catch (InvalidNewsContentException e) {
							System.out.println(e.getMessage());
							start();
					}
			}
			public void handleUpdateNewsRequest() {
					NewsModelDTO newsModelDTOToUpdate = null;
					try {
							newsModelDTOToUpdate = newsService.getNewsById(view.enterNewsToUpdateView());
					} catch (NoSuchNewsException e) {
							System.out.println(e.getMessage());
							start();
					}
					NewsModelDTO updatedNewsModelDTO = view.updateNewsView(newsModelDTOToUpdate);
					try {
							newsService.updateNews(updatedNewsModelDTO);
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
