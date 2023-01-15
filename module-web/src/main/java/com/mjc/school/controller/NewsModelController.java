package com.mjc.school.controller;

import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;

import java.util.List;

public interface NewsModelController {
		List<NewsModelDto> handleReadAllNewsRequest();
		NewsModelDto handleReadById(Long id) throws NoSuchNewsException;
		NewsModelDto handleCreateNewsRequest(NewsModelDto newsModelDto) throws InvalidNewsContentException;
		NewsModelDto handleUpdateNewsRequest(NewsModelDto newsToUpdate) throws NoSuchNewsException, InvalidNewsContentException;
		Boolean handleDeleteById(Long newsId) throws NoSuchNewsException;
}
