package com.mjc.school.repository.implementation;

import com.mjc.school.repository.DataSource;
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
						bw.append(DataSource.newsToString(news));
				} catch (IOException e) {
						e.printStackTrace();
				}
				return news;
		}

		@Override
		public List<News> readAll() {
				return DataSource.parseNewsFromFile();
		}

		@Override
		public News readById(Long id) {
				List<News> newsList = DataSource.parseNewsFromFile();
				return newsList.get(Math.toIntExact(id - 1));
		}

		@Override
		public News update(Long id, News updatedNews) {
				List<News> newsList = DataSource.parseNewsFromFile();
				News newsToUpdate = newsList.get(Math.toIntExact(id - 1));
				newsToUpdate.setTitle(updatedNews.getTitle());
				newsToUpdate.setContent(updatedNews.getContent());
				newsToUpdate.setLastUpdateDate(updatedNews.getLastUpdateDate());
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEWS_TXT))) {
						newsList.forEach(n -> {
								try {
										bw.write(DataSource.newsToString(n) + "\n");
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
		public Boolean deleteById(Long id) {
				List<News> newsList = new ArrayList<>(DataSource.parseNewsFromFile());
				newsList.remove(Math.toIntExact(id - 1));
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEWS_TXT))) {
						newsList.forEach(n -> {
								try {
										bw.write(DataSource.newsToString(n) + "\n");
								} catch (IOException e) {
										e.printStackTrace();
								}
						});
				} catch (IOException e) {
						e.printStackTrace();
				}
				return Boolean.TRUE;
		}
}
