package com.mjc.school.controller.implementation;

import com.mjc.school.controller.NewsModelController;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.NewsModelService;
import com.mjc.school.service.implementation.NewsModelServiceImpl;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/* Add tests for controller using mockito. */

@Data
@NoArgsConstructor
public class NewsModelControllerImpl implements NewsModelController {

		private final NewsModelService<NewsModelDto> newsModelService = new NewsModelServiceImpl();

			public List<NewsModelDto> readAllNewsRequest() {
					return newsModelService.readAllNews();
			}
			public NewsModelDto readByIdRequest(Long id) {
							 return newsModelService.readById(id);
			}
			public NewsModelDto createNewsRequest(NewsModelDto newsModelDto) {
							return newsModelService.createNewNews(newsModelDto);
			}
			public NewsModelDto updateNewsRequest(NewsModelDto newsToUpdate) {
					newsModelService.readById(newsToUpdate.getId());
					return newsModelService.updateNews(newsToUpdate);
			}
			public Boolean deleteByIdRequest(Long newsId) {
							return newsModelService.deleteNewsById(newsId);
			}
}
