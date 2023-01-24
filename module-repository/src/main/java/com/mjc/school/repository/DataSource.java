package com.mjc.school.repository;

import com.mjc.school.repository.model.NewsModel;
import lombok.AllArgsConstructor;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;


@AllArgsConstructor
public class DataSource {
		private String filePath;

		public List<NewsModel> parseNewsFromFile() {
				List<NewsModel> list = new ArrayList<>();
				try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))){
						list = fileReader.lines().map(this::stringToNews).toList();
				} catch (IOException e) {
						e.printStackTrace();
				}
				return list;
		}
		public void appendNewsToFile(NewsModel newsModel) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
						bw.write(newsToString(newsModel));
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
		public void saveAllToFile(List<NewsModel> newsModelList) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
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

		private NewsModel stringToNews(String s) {
				String[] stringArr = s.split(";");
				return new NewsModel(Long.parseLong(stringArr[0]),stringArr[1],stringArr[2], LocalDateTime.parse(stringArr[3]),LocalDateTime.parse(stringArr[4]),Long.parseLong(stringArr[5]));
		}

		private String newsToString(NewsModel newsModel) {
				StringJoiner sj = new StringJoiner(";");
				return sj.add(newsModel.getId().toString())
								.add(newsModel.getTitle())
								.add(newsModel.getContent())
								.add(newsModel.getCreateDate().toString())
								.add(newsModel.getLastUpdateDate().toString())
								.add(newsModel.getAuthorId().toString())
								.toString();
		}
}
