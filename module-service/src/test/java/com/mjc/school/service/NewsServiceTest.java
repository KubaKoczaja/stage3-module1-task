package com.mjc.school.service;

import com.mjc.school.repository.DTO.NewsDTO;
import com.mjc.school.repository.News;
import com.mjc.school.repository.NewsParser;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsServiceTest {
		private final NewsService newsService = new NewsService();
		private final LocalDateTime testDateTime = LocalDateTime.now();
		private final List<News> testList = List.of(new News(1L, "test", "test", testDateTime, testDateTime, 1L),
						new News(2L, "test", "test", testDateTime, testDateTime, 1L));
		private final MockedStatic<NewsParser> newsParserMockedStatic = Mockito.mockStatic(NewsParser.class);

		@BeforeEach
		void setUp() {
				newsParserMockedStatic.when(NewsParser::parseNewsFromFile).thenReturn(testList);
		}
		@AfterEach
		void tearDown() {
				newsParserMockedStatic.close();
		}
		@Test
		void shouldReturnAListOfAllNews() {
				int lengthExpected = 2;
				assertEquals(lengthExpected, newsService.getAllNews().size());
		}
		@SneakyThrows
		@Test
		void shouldReturnNewsDTOObjectWithGivenId() {
				NewsDTO expected = new NewsDTO(1L, "test", "test", testDateTime, testDateTime, 1L);
				assertEquals(expected, newsService.getNewsById(1L));
		}
		@SneakyThrows
		@Test
		void shouldThrowExceptionWhenThereIsNoNewsWithSpecificId() {
				assertThrows(NoSuchNewsException.class, () -> newsService.getNewsById(3L));
		}
		@SneakyThrows
		@Test
		void shouldReturnAddedObjectIfValuesAreCorrect() {
				NewsDTO expected = new NewsDTO(1L, "testTitle", "testContent", testDateTime, testDateTime, 1L);
				assertEquals(expected, newsService.addNewNews(expected));
		}
		@Test
		void shouldThrowExceptionIfValuesAreIncorrect() {
				NewsDTO expected = new NewsDTO(1L, "test", "test", testDateTime, testDateTime, 1L);
				assertThrows(InvalidNewsContentException.class, () -> newsService.addNewNews(expected));
		}
		@SneakyThrows
		@Test
		void shouldUpdateNewsWithGivenIdWhenValuesOfTiteAndContentAreCorrect() {
				NewsDTO expected = new NewsDTO(1L, "new_title", "new_content", testDateTime, testDateTime, 1L);
				assertEquals(expected, newsService.updateNews(expected));
		}
		@Test
		void shouldThrowExceptionWhenUpdatedValuesAreIncorrect() {
				NewsDTO expected = new NewsDTO(1L, "new", "new_content", testDateTime, testDateTime, 1L);
				assertThrows(InvalidNewsContentException.class, () -> newsService.updateNews(expected));
		}
		@SneakyThrows
		@Test
		void shouldReturnTrueIfNewsWasSuccessfullyRemoved() {
				assertTrue(newsService.removeNewsById(1L));
		}
		@Test
		void shouldThrowExceptionWhenRemovingNonExistingNews() {
				assertThrows(NoSuchNewsException.class, () -> newsService.removeNewsById(3L));
		}
}