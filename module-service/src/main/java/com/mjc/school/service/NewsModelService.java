package com.mjc.school.service;

import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;

import java.util.List;

public interface NewsModelService {
		List<NewsModelDto> readAllNews();
		NewsModelDto readById(Long id) throws NoSuchNewsException;
		NewsModelDto createNewNews(NewsModelDto newsModelDTO) throws InvalidNewsContentException;
		NewsModelDto updateNews (NewsModelDto updatedNewsModelDto) throws InvalidNewsContentException;
		Boolean deleteNewsById(Long newsId) throws NoSuchNewsException;

}
