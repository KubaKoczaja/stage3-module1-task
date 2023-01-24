package com.mjc.school.service;

import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.implementation.NewsModelServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.Month;

import static com.mjc.school.repository.FilePathUtils.TEST_CSV;
import static org.junit.jupiter.api.Assertions.*;
class NewsModelServiceTest {
		private final NewsModelService<NewsModelDto> newsService = new NewsModelServiceImpl();
		private final LocalDateTime testDateTime = LocalDateTime.of(2023, Month.JANUARY,10,12,15,10,0);
		/*
		* Here I tried to make this test use files with absolute paths from FilePathUtils, but as I mentioned on Slack it's not
		* working. Test can't locate them. When I used absolute path I did something like this:
		* try {
						Path news = Path.of(NEWS_TXT);
						Files.deleteIfExists(news);
						Files.copy(Path.of(TEST_TXT), news);
				} catch (IOException e) {
						throw new RuntimeException(e);
		* and it worked since all instances where created with this test data
		 */
		@BeforeEach
		void setUp() {
				try {
						URL resourceTest = getClass().getClassLoader().getResource("test.csv");
						File fileTest = new File(resourceTest.toURI());
						System.out.println(fileTest);
						URL resourceNews = getClass().getClassLoader().getResource("news.csv");
						File fileNews = new File(resourceNews.toURI());
						System.out.println(fileNews);
						Files.deleteIfExists(fileNews.toPath());
						Files.copy(fileTest.toPath(), fileNews.toPath());
				} catch (IOException | URISyntaxException e) {
						e.printStackTrace();
				}
		}
		

		@Test
		void shouldReturnAListOfAllNews() {
				int lengthExpected = 2;
				int lengthActual = newsService.readAllNews().size();
				assertEquals(lengthExpected, lengthActual);
		}
		@SneakyThrows
		@Test
		void shouldReturnNewsDTOObjectWithGivenId() {
				NewsModelDto expected = new NewsModelDto(1L, "test", "test", testDateTime, testDateTime, 1L);
				assertEquals(expected, newsService.readById(1L));
		}
		@SneakyThrows
		@Test
		void shouldThrowExceptionWhenThereIsNoNewsWithSpecificId() {
				assertThrows(NoSuchNewsException.class, () -> newsService.readById(3L));
		}
		@SneakyThrows
		@Test
		void shouldReturnAddedObjectIfValuesAreCorrect() {
				NewsModelDto expected = new NewsModelDto(1L, "testTitle", "testContent", testDateTime, testDateTime, 1L);
				assertEquals(expected, newsService.createNewNews(expected));
		}
		@Test
		void shouldThrowExceptionIfValuesAreIncorrect() {
				NewsModelDto expected = new NewsModelDto(1L, "test", "test", testDateTime, testDateTime, 1L);
				assertThrows(InvalidNewsContentException.class, () -> newsService.createNewNews(expected));
		}
		@SneakyThrows
		@Test
		void shouldUpdateNewsWithGivenIdWhenValuesOfTitleAndContentAreCorrect() {
				NewsModelDto expected = new NewsModelDto(1L, "new_title", "new_content", testDateTime, testDateTime, 1L);
				assertEquals(expected, newsService.updateNews(expected));
		}
		@Test
		void shouldThrowExceptionWhenUpdatedValuesAreIncorrect() {
				NewsModelDto expected = new NewsModelDto(1L, "new", "new_content", testDateTime, testDateTime, 1L);
				assertThrows(InvalidNewsContentException.class, () -> newsService.updateNews(expected));
		}
		@SneakyThrows
		@Test
		void shouldReturnTrueIfNewsWasSuccessfullyRemoved() {
				assertTrue(newsService.deleteNewsById(1L));
		}
		@Test
		void shouldThrowExceptionWhenRemovingNonExistingNews() {
				assertThrows(NoSuchNewsException.class, () -> newsService.deleteNewsById(3L));
		}
}