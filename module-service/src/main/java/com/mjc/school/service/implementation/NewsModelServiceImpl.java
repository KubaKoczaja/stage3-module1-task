package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsModelRepository;
import com.mjc.school.repository.implementation.NewsModelRepositoryImpl;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.NewsModelService;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.NewsMapperImpl;
import com.mjc.school.service.validator.InputValidator;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class NewsModelServiceImpl implements NewsModelService {
		private final NewsMapper newsMapper = new NewsMapperImpl();
		private final NewsModelRepository newsRepository = new NewsModelRepositoryImpl();
		private final InputValidator inputValidator = new InputValidator();

		public List<NewsModelDto> readAllNews() {
				return newsRepository.readAll().stream().map(newsMapper::newsToNewsDTO).toList();
		}
		public NewsModelDto readById(Long id) throws NoSuchNewsException {
				 if (!inputValidator.validateIfNewsWithIdExists(id, readAllNews())) {
						 throw new NoSuchNewsException("No news with such id!");
				 }
				return newsMapper.newsToNewsDTO(newsRepository.readById(id));
		}
		public NewsModelDto createNewNews(NewsModelDto newsModelDTO) throws InvalidNewsContentException {
				if (!inputValidator.validateProperLengthOfString(newsModelDTO.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!inputValidator.validateProperLengthOfString(newsModelDTO.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				NewsModel createdNewsModel = newsRepository.create(newsMapper.newsDTOToNews(newsModelDTO));
				return newsMapper.newsToNewsDTO(createdNewsModel);
		}

		public NewsModelDto updateNews (NewsModelDto updatedNewsModelDto) throws InvalidNewsContentException {
				if (!inputValidator.validateProperLengthOfString(updatedNewsModelDto.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!inputValidator.validateProperLengthOfString(updatedNewsModelDto.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				NewsModel updatedNewsModel = newsRepository.update(newsMapper.newsDTOToNews(updatedNewsModelDto));
				return newsMapper.newsToNewsDTO(updatedNewsModel);
		}

		public Boolean deleteNewsById(Long newsId) throws NoSuchNewsException {
				if (!inputValidator.validateIfNewsWithIdExists(newsId, readAllNews())) {
						throw new NoSuchNewsException("No news with such id!");
				}
				return newsRepository.deleteById(newsId);
		}
}
