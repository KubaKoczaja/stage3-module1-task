package com.mjc.school.repository.implementation;

import com.mjc.school.repository.NewsParser;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.News;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {
		public static final String NEWS_TXT = "module-repository/src/main/resources/news.txt";
		@Override
		public News create(News news) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEWS_TXT, true))) {
						bw.append(NewsParser.newsToString(news));
				} catch (IOException e) {
						e.printStackTrace();
				}
				return news;
		}

		@Override
		public List<News> readAll() {
				return NewsParser.parseNewsFromFile();
		}

		@Override
		public News readById(long id) {
				List<News> newsList = NewsParser.parseNewsFromFile();
				return newsList.get(Math.toIntExact(id - 1));
		}

		@Override
		public News update(long id, News updatedNews) {
				List<News> newsList = NewsParser.parseNewsFromFile();
				News newsToUpdate = newsList.get(Math.toIntExact(id - 1));
				newsToUpdate.setTitle(updatedNews.getTitle());
				newsToUpdate.setContent(updatedNews.getContent());
				newsToUpdate.setLastUpdateDate(updatedNews.getLastUpdateDate());
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEWS_TXT))) {
						newsList.forEach(n -> {
								try {
										bw.write(NewsParser.newsToString(n) + "\n");
								} catch (IOException e) {
										e.printStackTrace();
								}
						});
				} catch (IOException e) {
						e.printStackTrace();
				}
				return updatedNews;
		}

		@Override
		public boolean deleteById(long id) {
				List<News> newsList = new ArrayList<>(NewsParser.parseNewsFromFile());
				newsList.remove(Math.toIntExact(id - 1));
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEWS_TXT))) {
						newsList.forEach(n -> {
								try {
										bw.write(NewsParser.newsToString(n) + "\n");
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
