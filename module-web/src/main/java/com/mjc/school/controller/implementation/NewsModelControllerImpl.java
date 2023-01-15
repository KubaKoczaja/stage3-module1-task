package com.mjc.school.controller.implementation;

import com.mjc.school.controller.NewsModelController;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.implementation.NewsModelServiceImpl;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class NewsModelControllerImpl implements NewsModelController {
		private final NewsModelServiceImpl newsService = new NewsModelServiceImpl();

			public List<NewsModelDto> handleReadAllNewsRequest() {
					return newsService.readAllNews();
			}

			public NewsModelDto handleReadById(Long id) throws NoSuchNewsException {
							 return newsService.readById(id);
			}
			public NewsModelDto handleCreateNewsRequest(NewsModelDto newsModelDto) throws InvalidNewsContentException {
							return newsService.createNewNews(newsModelDto);
			}
			public NewsModelDto handleUpdateNewsRequest(NewsModelDto newsToUpdate) throws NoSuchNewsException, InvalidNewsContentException {
					newsService.readById(newsToUpdate.getId());
					return newsService.updateNews(newsToUpdate);
			}
			public Boolean handleDeleteById(Long newsId) throws NoSuchNewsException {
							return newsService.deleteNewsById(newsId);
			}
}
