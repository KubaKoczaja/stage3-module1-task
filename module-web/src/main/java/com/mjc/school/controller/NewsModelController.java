package com.mjc.school.controller;

import com.mjc.school.repository.model.dto.NewsModelDto;

import java.util.List;

public interface NewsModelController {
		List<NewsModelDto> readAllNewsRequest();
		NewsModelDto readByIdRequest(Long id);
		NewsModelDto createNewsRequest(NewsModelDto newsModelDto);
		NewsModelDto updateNewsRequest(NewsModelDto newsToUpdate);
		Boolean deleteByIdRequest(Long newsId);
}
