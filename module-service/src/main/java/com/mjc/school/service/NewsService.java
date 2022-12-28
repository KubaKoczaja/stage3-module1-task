package com.mjc.school.service;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.model.dto.NewsDTO;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.NewsMapperImpl;
import com.mjc.school.service.validator.InputValidator;

import java.util.List;

public class NewsService {
		private final NewsMapper newsMapper = new NewsMapperImpl();
		private  final NewsRepository newsRepository = new NewsRepositoryImpl();

		public List<NewsDTO> getAllNews() {
				return newsRepository.readAll().stream().map(newsMapper::newsToNewsDTO).toList();
		}
		public NewsDTO getNewsById(Long id) throws NoSuchNewsException {
				 if (!InputValidator.validateIfNewsWithIdExists(id, getAllNews())) {
						 throw new NoSuchNewsException("No news with such id!");
				 }
				return newsMapper.newsToNewsDTO(newsRepository.readById(id));
		}
		public NewsDTO addNewNews(NewsDTO newsDTO) throws InvalidNewsContentException {
				if (!InputValidator.validateProperLengthOfString(newsDTO.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!InputValidator.validateProperLengthOfString(newsDTO.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				News createdNews = newsRepository.create(newsMapper.newsDTOToNews(newsDTO));
				return newsMapper.newsToNewsDTO(createdNews);
		}

		public NewsDTO updateNews (NewsDTO updatedNewsDTO) throws InvalidNewsContentException {
				if (!InputValidator.validateProperLengthOfString(updatedNewsDTO.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!InputValidator.validateProperLengthOfString(updatedNewsDTO.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				News updatedNews = newsRepository.update(updatedNewsDTO.getId(), newsMapper.newsDTOToNews(updatedNewsDTO));
				return newsMapper.newsToNewsDTO(updatedNews);
		}

		public boolean removeNewsById(Long newsId) throws NoSuchNewsException {
				if (!InputValidator.validateIfNewsWithIdExists(newsId, getAllNews())) {
						throw new NoSuchNewsException("No news with such id!");
				}
				return newsRepository.deleteById(newsId);
		}
}
