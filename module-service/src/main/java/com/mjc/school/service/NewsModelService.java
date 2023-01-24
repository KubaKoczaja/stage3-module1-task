package com.mjc.school.service;

import java.util.List;

public interface NewsModelService<T> {
		List<T> readAllNews();
		T readById(Long id);
		T createNewNews(T newsModelDTO);
		T updateNews (T updatedNewsModelDto);
		Boolean deleteNewsById(Long newsId);

}
