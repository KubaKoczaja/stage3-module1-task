package com.mjc.school.controller.implementation;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.NewsModelService;
import com.mjc.school.service.implementation.NewsModelServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.mjc.school.*")
class NewsModelControllerTest {

		private NewsModelControllerImpl newsModelController;

		private NewsModelServiceImpl newsModelService;

		/*
		* In these test I tried using PowerMock, but it doesn't work. When creating instance of controller it is still using
		* NewsService created with data from file news.csv. At least it is trying to use data from this file, because it
		* can't locate it when using relative path (when using absolute path probably it would work)
		 */
		@BeforeEach
		 void setUpt() throws Exception {
				newsModelService = PowerMockito.mock(NewsModelServiceImpl.class);
				whenNew(NewsModelServiceImpl.class).withNoArguments().thenReturn(newsModelService);
				newsModelController = new NewsModelControllerImpl();
		}
		@Test
		void shouldReadAllNews() throws Exception {
				List<NewsModelDto> list = List.of(new NewsModelDto(1L,"test","test", LocalDateTime.now(), LocalDateTime.now(), 1L),new NewsModelDto(1L,"test","test", LocalDateTime.now(), LocalDateTime.now(), 1L));
				when(newsModelService.readAllNews()).thenReturn(list);
				assertEquals(2, newsModelController.readAllNewsRequest().size());
		}
		@Disabled
		@Test
		void readByIdRequest() {
		}
		@Disabled
		@Test
		void createNewsRequest() {
		}
		@Disabled
		@Test
		void updateNewsRequest() {
		}
		@Disabled
		@Test
		void deleteByIdRequest() {
		}
}