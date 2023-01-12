package com.mjc.school.service;

import com.mjc.school.repository.model.dto.NewsModelDTO;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;

import java.util.List;

public interface NewsService {
		List<NewsModelDTO> readAllNews();
		NewsModelDTO readNewsById (Long id) throws NoSuchNewsException;
		NewsModelDTO createNewNews(NewsModelDTO newsModelDTO) throws InvalidNewsContentException;
		NewsModelDTO updateNews (NewsModelDTO updatedNewsModelDTO) throws InvalidNewsContentException;
		Boolean deleteNewsById(Long newsId) throws NoSuchNewsException;

}
