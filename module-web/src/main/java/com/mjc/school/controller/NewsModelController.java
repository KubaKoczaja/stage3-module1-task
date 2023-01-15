package com.mjc.school.controller;

import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;

import java.util.List;

public interface NewsModelController {
		List<NewsModelDto> readAllNewsRequest();
		NewsModelDto readByIdRequest(Long id) throws NoSuchNewsException;
		NewsModelDto createNewsRequest(NewsModelDto newsModelDto) throws InvalidNewsContentException;
		NewsModelDto updateNewsRequest(NewsModelDto newsToUpdate) throws NoSuchNewsException, InvalidNewsContentException;
		Boolean deleteByIdRequest(Long newsId) throws NoSuchNewsException;
}
