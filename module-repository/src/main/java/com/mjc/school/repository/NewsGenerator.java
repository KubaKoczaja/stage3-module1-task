package com.mjc.school.repository;

import com.mjc.school.repository.model.NewsModel;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NewsGenerator {
		private NewsGenerator() {		}

		public static void generateNews() {
				List<NewsModel> newsModelList = new ArrayList<>();
				List<String> list = new ArrayList<>();

				try (BufferedReader fileReader = new BufferedReader(new FileReader("module-repository/src/main/resources/content.txt"))){
						list = fileReader.lines().toList();
				} catch (IOException e) {
						e.printStackTrace();
				}
				String[] titles = list.get(0).split(",");
				String[] content = list.subList(1, list.size() - 1).toArray(new String[0]);
				for (long i = 1L; i <= 20; i++) {
						NewsModel newsModel = new NewsModel();
						newsModel.setId(i);
						newsModel.setTitle(titles[ThreadLocalRandom.current().nextInt(0, titles.length)]);
						newsModel.setContent(content[ThreadLocalRandom.current().nextInt(0, content.length)]);
						LocalDateTime randomDate = randomDate();
						newsModel.setCreateDate(randomDate);
						newsModel.setLastUpdateDate(randomDate);
						newsModel.setAuthorId(ThreadLocalRandom.current().nextLong(1, 7));
						newsModelList.add(newsModel);
				}
				try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("module-repository/src/main/resources/news.txt"))){
						newsModelList.forEach(s -> {
								try {
										bufferedWriter.write(DataSource.newsToString(s) + "\n");
								} catch (IOException e) {
										e.printStackTrace();
								}
						});
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
		public static LocalDateTime randomDate() {
				long startMillis = LocalDateTime.now().minus(Duration.ofDays(10)).toEpochSecond(ZoneOffset.ofHours(0));
				long endMillis = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(0));
				long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);

				return LocalDateTime.ofEpochSecond(randomMillisSinceEpoch, 0, ZoneOffset.ofHours(0));
		}
}