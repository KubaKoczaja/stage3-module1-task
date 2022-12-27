package com.mjc.school.service;

import com.mjc.school.repository.DTO.NewsDTO;
import com.mjc.school.repository.News;
import com.mjc.school.repository.NewsParser;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.mapper.NewsMapperImpl;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validator.InputValidator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsService {
		public static final String NEWS_TXT = "module-repository/src/main/resources/news.txt";
		private final NewsMapper newsMapper = new NewsMapperImpl();
		public List<NewsDTO> getAllNews() {
				List<News> newsList = NewsParser.parseNewsFromFile();
				return newsList.stream().map(newsMapper::newsToNewsDTO).toList();
		}
		public NewsDTO getNewsById(Long id) throws NoSuchNewsException {
				List<NewsDTO> newsList = getAllNews();
				 if (!InputValidator.validateIfNewsWithIdExists(id, newsList)) {
						 throw new NoSuchNewsException("No news with such id!");
				 }
				return newsList.get((id.intValue() - 1));
		}
		public NewsDTO addNewNews(NewsDTO newsDTO) throws InvalidNewsContentException {
				if (!InputValidator.validateProperLengthOfString(newsDTO.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				if (!InputValidator.validateProperLengthOfString(newsDTO.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEWS_TXT, true))) {
						bw.append(NewsParser.newsToString(newsMapper.newsDTOToNews(newsDTO)));
				} catch (IOException e) {
						e.printStackTrace();
				}
				return newsDTO;
		}

		public NewsDTO updateNews (NewsDTO newsDTOToSave) throws InvalidNewsContentException {
				List<NewsDTO> newsList = getAllNews();
				NewsDTO oldNewsDTO = newsList.get(Math.toIntExact(newsDTOToSave.getId()) - 1);
				if (!InputValidator.validateProperLengthOfString(newsDTOToSave.getTitle(), 5, 30)) {
						throw new InvalidNewsContentException("Title of message should be between 5 and 30 characters length!");
				}
				oldNewsDTO.setTitle(newsDTOToSave.getTitle());
				if (!InputValidator.validateProperLengthOfString(newsDTOToSave.getContent(), 5, 255)) {
						throw new InvalidNewsContentException("Content of message should be between 5 and 255 characters length!");
				}
				oldNewsDTO.setContent(newsDTOToSave.getContent());
				oldNewsDTO.setLastUpdateDate(newsDTOToSave.getLastUpdateDate());
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEWS_TXT))) {
						newsList.forEach(n -> {
								try {
										bw.write(NewsParser.newsToString(newsMapper.newsDTOToNews(n)) + "\n");
								} catch (IOException e) {
										e.printStackTrace();
								}
						});
				} catch (IOException e) {
						e.printStackTrace();
				}
				return newsList.get(Math.toIntExact(oldNewsDTO.getId()) - 1);
		}

		public boolean removeNewsById(Long newsId) throws NoSuchNewsException {
				List<NewsDTO> list = getAllNews();
				if (!InputValidator.validateIfNewsWithIdExists(newsId, list)) {
						throw new NoSuchNewsException("No news with such id!");
				}
				List<NewsDTO> newsList = new ArrayList<>(list);
				newsList.remove(Math.toIntExact(newsId) - 1);
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEWS_TXT))) {
						newsList.forEach(n -> {
								try {
										bw.write(NewsParser.newsToString(newsMapper.newsDTOToNews(n)) + "\n");
								} catch (IOException e) {
										e.printStackTrace();
								}
						});
				} catch (IOException e) {
						e.printStackTrace();
				}
				return true;
		}
}
