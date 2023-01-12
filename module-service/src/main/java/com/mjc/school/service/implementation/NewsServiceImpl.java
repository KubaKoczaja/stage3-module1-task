package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.NewsModelDTO;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.NewsMapperImpl;
import com.mjc.school.service.validator.InputValidator;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class NewsServiceImpl implements NewsService {
		private final NewsMapper newsMapper = new NewsMapperImpl();
		private final NewsRepository newsRepository = new NewsRepositoryImpl();
		private final InputValidator inputValidator = new InputValidator();

		public List<NewsModelDTO> readAllNews() {
				return newsRepository.readAll().stream().map(newsMapper::newsToNewsDTO).toList();
		}
		public NewsModelDTO readNewsById (Long id) throws NoSuchNewsException {
				 if (!inputValidator.validateIfNewsWithIdExists(id, readAllNews())) {
						 throw new NoSuchNewsException("No news with such id!");
				 }
				return newsMapper.newsToNewsDTO(newsRepository.readById(id));
		}
		public NewsModelDTO createNewNews(NewsModelDTO newsModelDTO) throws InvalidNewsContentException {
				if (!inputValidator.validateProperLengthOfString(newsModelDTO.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!inputValidator.validateProperLengthOfString(newsModelDTO.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				NewsModel createdNewsModel = newsRepository.create(newsMapper.newsDTOToNews(newsModelDTO));
				return newsMapper.newsToNewsDTO(createdNewsModel);
		}

		public NewsModelDTO updateNews (NewsModelDTO updatedNewsModelDTO) throws InvalidNewsContentException {
				if (!inputValidator.validateProperLengthOfString(updatedNewsModelDTO.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!inputValidator.validateProperLengthOfString(updatedNewsModelDTO.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				NewsModel updatedNewsModel = newsRepository.update(newsMapper.newsDTOToNews(updatedNewsModelDTO));
				return newsMapper.newsToNewsDTO(updatedNewsModel);
		}

		public Boolean deleteNewsById(Long newsId) throws NoSuchNewsException {
				if (!inputValidator.validateIfNewsWithIdExists(newsId, readAllNews())) {
						throw new NoSuchNewsException("No news with such id!");
				}
				return newsRepository.deleteById(newsId);
		}
}
