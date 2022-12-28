package com.mjc.school.repository;

import com.mjc.school.repository.model.News;

import java.util.List;

public interface NewsRepository {
		News create(News news);
		List<News> readAll();
		News readById(long id);
		News update(long id, News updatedNews);
		Boolean deleteById(long id);
}
