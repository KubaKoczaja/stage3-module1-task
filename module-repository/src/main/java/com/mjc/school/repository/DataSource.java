package com.mjc.school.repository;

import com.mjc.school.repository.model.NewsModel;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class DataSource {
		public static final String NEWS_TXT = "module-repository/src/main/resources/news.txt";
		private DataSource(){}

		public static List<NewsModel> parseNewsFromFile() {
				List<NewsModel> list = new ArrayList<>();
				try (BufferedReader fileReader = new BufferedReader(new FileReader(NEWS_TXT))){
						list = fileReader.lines().map(DataSource::stringToNews).toList();
				} catch (IOException e) {
						e.printStackTrace();
				}
				return list;
		}
		public static void appendNewsToFile(NewsModel newsModel) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEWS_TXT, true))) {
						bw.append(newsToString(newsModel));
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
		public static void saveAllToFile(List<NewsModel> newsModelList) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEWS_TXT))) {
						newsModelList.forEach(n -> {
								try {
										bw.write(newsToString(n) + "\n");
								} catch (IOException e) {
										e.printStackTrace();
								}
						});
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		public static NewsModel stringToNews(String s) {
				String[] stringArr = s.split("<>");
				return new NewsModel(Long.parseLong(stringArr[0]),stringArr[1],stringArr[2], LocalDateTime.parse(stringArr[3]),LocalDateTime.parse(stringArr[4]),Long.parseLong(stringArr[5]));
		}

		public static String newsToString(NewsModel newsModel) {
				StringJoiner sj = new StringJoiner("<>");
				return sj.add(newsModel.getId().toString())
								.add(newsModel.getTitle())
								.add(newsModel.getContent())
								.add(newsModel.getCreateDate().toString())
								.add(newsModel.getLastUpdateDate().toString())
								.add(newsModel.getAuthorId().toString())
								.toString();
		}
}
