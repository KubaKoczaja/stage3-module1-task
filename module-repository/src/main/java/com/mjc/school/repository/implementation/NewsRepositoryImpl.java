package com.mjc.school.repository.implementation;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {
		@Override
		public NewsModel create(NewsModel newsModel) {
				DataSource.appendNewsToFile(newsModel);
				return newsModel;
		}

		@Override
		public List<NewsModel> readAll() {
				return DataSource.parseNewsFromFile();
		}

		@Override
		public NewsModel readById(Long id) {
				List<NewsModel> newsModelList = DataSource.parseNewsFromFile();
				return newsModelList.get(Math.toIntExact(id - 1));
		}

		@Override
		public NewsModel update(Long id, NewsModel updatedNewsModel) {
				List<NewsModel> newsModelList = DataSource.parseNewsFromFile();
				NewsModel newsModelToUpdate = newsModelList.get(Math.toIntExact(id - 1));
				newsModelToUpdate.setTitle(updatedNewsModel.getTitle());
				newsModelToUpdate.setContent(updatedNewsModel.getContent());
				newsModelToUpdate.setLastUpdateDate(updatedNewsModel.getLastUpdateDate());
				DataSource.saveAllToFile(newsModelList);
				return updatedNewsModel;
		}

		@Override
		public Boolean deleteById(Long id) {
				List<NewsModel> newsModelList = new ArrayList<>(DataSource.parseNewsFromFile());
				newsModelList.remove(Math.toIntExact(id - 1));
				DataSource.saveAllToFile(newsModelList);
				return Boolean.TRUE;
		}
}
