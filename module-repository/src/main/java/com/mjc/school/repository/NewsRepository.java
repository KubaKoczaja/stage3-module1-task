package com.mjc.school.repository;

import com.mjc.school.repository.model.NewsModel;

import java.util.List;

public interface NewsRepository {
		NewsModel create(NewsModel newsModel);
		List<NewsModel> readAll();
		NewsModel readById(Long id);
		NewsModel update(Long id, NewsModel updatedNewsModel);
		Boolean deleteById(Long id);
}
