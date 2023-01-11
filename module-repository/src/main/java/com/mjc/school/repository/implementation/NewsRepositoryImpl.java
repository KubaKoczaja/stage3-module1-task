package com.mjc.school.repository.implementation;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.FilePathUtils;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {
		private final DataSource dataSource = new DataSource(FilePathUtils.NEWS_TXT);
		@Override
		public NewsModel create(NewsModel newsModel) {
				dataSource.appendNewsToFile(newsModel);
				return newsModel;
		}

		@Override
		public List<NewsModel> readAll() {
				return dataSource.parseNewsFromFile();
		}

		@Override
		public NewsModel readById(Long id) {
				List<NewsModel> newsModelList = dataSource.parseNewsFromFile();
				return newsModelList.get(Math.toIntExact(id - 1));
		}

		@Override
		public NewsModel update(NewsModel updatedNewsModel) {
				List<NewsModel> newsModelList = dataSource.parseNewsFromFile();
				NewsModel newsModelToUpdate = newsModelList.get(Math.toIntExact(updatedNewsModel.getId() - 1));
				newsModelToUpdate.setTitle(updatedNewsModel.getTitle());
				newsModelToUpdate.setContent(updatedNewsModel.getContent());
				newsModelToUpdate.setLastUpdateDate(updatedNewsModel.getLastUpdateDate());
				dataSource.saveAllToFile(newsModelList);
				return updatedNewsModel;
		}

		@Override
		public Boolean deleteById(Long id) {
				List<NewsModel> newsModelList = new ArrayList<>(dataSource.parseNewsFromFile());
				newsModelList.remove(Math.toIntExact(id - 1));
				dataSource.saveAllToFile(newsModelList);
				return Boolean.TRUE;
		}
}
