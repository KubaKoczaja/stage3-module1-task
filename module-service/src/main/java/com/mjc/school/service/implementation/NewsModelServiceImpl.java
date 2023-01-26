package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsModelRepository;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.NewsModelService;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validator.InputValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class NewsModelServiceImpl implements NewsModelService<NewsModelDto> {
		private final NewsMapper newsMapper;
		private final NewsModelRepository newsRepository;
		private final InputValidator inputValidator;

		public List<NewsModelDto> readAllNews() {
				return newsRepository.readAll().stream().map(newsMapper::newsToNewsDTO).toList();
		}
		public NewsModelDto readById(Long id) {
				 if (!inputValidator.validateIfNewsWithIdExists(id, readAllNews())) {
						 throw new NoSuchNewsException("No news with such id!");
				 }
				return newsMapper.newsToNewsDTO(newsRepository.readById(id));
		}
		public NewsModelDto createNewNews(NewsModelDto newsModelDTO) {
				if (!inputValidator.validateProperLengthOfString(newsModelDTO.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!inputValidator.validateProperLengthOfString(newsModelDTO.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				newsRepository.create(newsMapper.newsDTOToNews(newsModelDTO));
				return newsModelDTO;
		}

		public NewsModelDto updateNews (NewsModelDto updatedNewsModelDto) {
				if (!inputValidator.validateProperLengthOfString(updatedNewsModelDto.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!inputValidator.validateProperLengthOfString(updatedNewsModelDto.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				newsRepository.update(newsMapper.newsDTOToNews(updatedNewsModelDto));
				return updatedNewsModelDto;
		}

		public Boolean deleteNewsById(Long newsId) {
				if (!inputValidator.validateIfNewsWithIdExists(newsId, readAllNews())) {
						throw new NoSuchNewsException("No news with such id!");
				}
				return newsRepository.deleteById(newsId);
		}
}
