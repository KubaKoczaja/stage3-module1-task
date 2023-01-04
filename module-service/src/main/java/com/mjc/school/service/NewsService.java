package com.mjc.school.service;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.NewsModelDTO;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.NewsMapperImpl;
import com.mjc.school.service.validator.InputValidator;

import java.util.List;

public class NewsService {
		private final NewsMapper newsMapper = new NewsMapperImpl();
		private  final NewsRepository newsRepository = new NewsRepositoryImpl();

		public List<NewsModelDTO> getAllNews() {
				return newsRepository.readAll().stream().map(newsMapper::newsToNewsDTO).toList();
		}
		public NewsModelDTO getNewsById(Long id) throws NoSuchNewsException {
				 if (!InputValidator.validateIfNewsWithIdExists(id, getAllNews())) {
						 throw new NoSuchNewsException("No news with such id!");
				 }
				return newsMapper.newsToNewsDTO(newsRepository.readById(id));
		}
		public NewsModelDTO addNewNews(NewsModelDTO newsModelDTO) throws InvalidNewsContentException {
				if (!InputValidator.validateProperLengthOfString(newsModelDTO.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!InputValidator.validateProperLengthOfString(newsModelDTO.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				NewsModel createdNewsModel = newsRepository.create(newsMapper.newsDTOToNews(newsModelDTO));
				return newsMapper.newsToNewsDTO(createdNewsModel);
		}

		public NewsModelDTO updateNews (NewsModelDTO updatedNewsModelDTO) throws InvalidNewsContentException {
				if (!InputValidator.validateProperLengthOfString(updatedNewsModelDTO.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!InputValidator.validateProperLengthOfString(updatedNewsModelDTO.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				NewsModel updatedNewsModel = newsRepository.update(updatedNewsModelDTO.getId(), newsMapper.newsDTOToNews(updatedNewsModelDTO));
				return newsMapper.newsToNewsDTO(updatedNewsModel);
		}

		public boolean removeNewsById(Long newsId) throws NoSuchNewsException {
				if (!InputValidator.validateIfNewsWithIdExists(newsId, getAllNews())) {
						throw new NoSuchNewsException("No news with such id!");
				}
				return newsRepository.deleteById(newsId);
		}
}
