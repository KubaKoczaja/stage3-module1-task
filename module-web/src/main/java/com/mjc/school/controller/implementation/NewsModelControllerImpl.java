package com.mjc.school.controller.implementation;

import com.mjc.school.controller.NewsModelController;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.NewsModelService;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.implementation.NewsModelServiceImpl;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class NewsModelControllerImpl implements NewsModelController {
		private final NewsModelService newsModelService = new NewsModelServiceImpl();

			public List<NewsModelDto> readAllNewsRequest() {
					return newsModelService.readAllNews();
			}

			public NewsModelDto readByIdRequest(Long id) throws NoSuchNewsException {
							 return newsModelService.readById(id);
			}
			public NewsModelDto createNewsRequest(NewsModelDto newsModelDto) throws InvalidNewsContentException {
							return newsModelService.createNewNews(newsModelDto);
			}
			public NewsModelDto updateNewsRequest(NewsModelDto newsToUpdate) throws NoSuchNewsException, InvalidNewsContentException {
					newsModelService.readById(newsToUpdate.getId());
					return newsModelService.updateNews(newsToUpdate);
			}
			public Boolean deleteByIdRequest(Long newsId) throws NoSuchNewsException {
							return newsModelService.deleteNewsById(newsId);
			}
}
