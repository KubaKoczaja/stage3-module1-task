package com.mjc.school.repository;

import com.mjc.school.repository.model.News;

import java.util.List;

public interface NewsRepository {
		News create(News news);
		List<News> readAll();
		News readById(Long id);
		News update(Long id, News updatedNews);
		Boolean deleteById(Long id);
}
