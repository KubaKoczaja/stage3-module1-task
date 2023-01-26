package com.mjc.school;

import com.mjc.school.controller.NewsModelController;
import com.mjc.school.controller.implementation.NewsModelControllerImpl;
import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.FilePathUtils;
import com.mjc.school.repository.NewsGenerator;
import com.mjc.school.repository.NewsModelRepository;
import com.mjc.school.repository.implementation.NewsModelRepositoryImpl;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.NewsModelService;
import com.mjc.school.service.implementation.NewsModelServiceImpl;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.NewsMapperImpl;
import com.mjc.school.service.validator.InputValidator;
import com.mjc.school.web.View;

public class AppMain {
		public static void main(String[] args) {
				NewsGenerator newsGenerator = new NewsGenerator();
				newsGenerator.generateNews();
				DataSource dataSource = new DataSource(FilePathUtils.NEWS_CSV);
				NewsMapper newsMapper = new NewsMapperImpl();
				NewsModelRepository newsModelRepository = new NewsModelRepositoryImpl(dataSource);
				InputValidator inputValidator = new InputValidator();
				NewsModelService<NewsModelDto> newsModelService = new NewsModelServiceImpl(newsMapper, newsModelRepository, inputValidator);
				NewsModelController newsModelController = new NewsModelControllerImpl(newsModelService);
				View view = new View(newsModelController);
				view.start();
		}
}
