package com.mjc.school.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class NewsParser {
		public static List<News> parseNewsFromFile() {
				List<News> list = new ArrayList<>();
				try (BufferedReader fileReader = new BufferedReader(new FileReader("module-repository/src/main/resources/news.txt"))){
						list = fileReader.lines().map(NewsParser::stringToNews).toList();
				} catch (IOException e) {
						e.printStackTrace();
				}
				return list;
		}

		public static News stringToNews(String s) {
				String[] stringArr = s.split("<>");
				return new News(Long.parseLong(stringArr[0]),stringArr[1],stringArr[2], LocalDateTime.parse(stringArr[3]),LocalDateTime.parse(stringArr[4]),Long.parseLong(stringArr[5]));
		}

		public static String newsToString(News news) {
				StringJoiner sj = new StringJoiner("<>");
				return sj.add(news.getId().toString())
								.add(news.getTitle())
								.add(news.getContent())
								.add(news.getCreateDate().toString())
								.add(news.getLastUpdateDate().toString())
								.add(news.getAuthorId().toString())
								.toString();
		}
}
