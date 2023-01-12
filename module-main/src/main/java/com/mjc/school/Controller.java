package com.mjc.school;

import com.mjc.school.repository.NewsGenerator;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.implementation.NewsModelServiceImpl;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.web.View;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class Controller {
		private final View view = new View();
		private final NewsModelServiceImpl newsService = new NewsModelServiceImpl();
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
					List<NewsModelDto> newsList = newsService.readAllNews();
					view.allNewsView(newsList);
			}

			public void handleNewsById() {
					try {
							System.out.println(newsService.readById(view.newsByIdView()));
					} catch (NoSuchNewsException e) {
							System.out.println(e.getMessage());
							start();
					}
			}

			public void handleCreateNewsRequest() {
					NewsModelDto newsModelDtoInput = view.createNewsView();
					try {
							newsService.createNewNews(newsModelDtoInput);
					} catch (InvalidNewsContentException e) {
							System.out.println(e.getMessage());
							start();
					}
			}
			public void handleUpdateNewsRequest() {
					NewsModelDto newsModelDtoToUpdate = null;
					try {
							newsModelDtoToUpdate = newsService.readById(view.enterNewsToUpdateView());
					} catch (NoSuchNewsException e) {
							System.out.println(e.getMessage());
							start();
					}
					NewsModelDto updatedNewsModelDto = view.updateNewsView(newsModelDtoToUpdate);
					try {
							newsService.updateNews(updatedNewsModelDto);
					} catch (InvalidNewsContentException e) {
							System.out.println(e.getMessage());
							start();
					}
			}
			public void handleRemoveNewsById() {
					try {
							newsService.deleteNewsById(view.enterNewsToRemoveView());
					} catch (NoSuchNewsException e) {
							System.out.println(e.getMessage());
							start();
					}
			}
			public void handleExitRequest() {
					System.out.println("Exit");
			}
}
