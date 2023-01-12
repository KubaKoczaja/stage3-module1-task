package com.mjc.school.service;

import com.mjc.school.repository.model.dto.NewsModelDTO;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.implementation.NewsServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.Month;

import static com.mjc.school.repository.FilePathUtils.NEWS_TXT;
import static com.mjc.school.repository.FilePathUtils.TEST_TXT;
import static org.junit.jupiter.api.Assertions.*;
class NewsModelServiceTest {
		private final NewsServiceImpl newsService = new NewsServiceImpl();
		private final LocalDateTime testDateTime = LocalDateTime.of(2023, Month.JANUARY,10,12,15,10,0);
		@BeforeEach
		void setUp() {
				try {
						Path news = Path.of(NEWS_TXT);
						Files.deleteIfExists(news);
						Files.copy(Path.of(TEST_TXT), news);
				} catch (IOException e) {
						throw new RuntimeException(e);
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
				NewsModelDTO expected = new NewsModelDTO(1L, "test", "test", testDateTime, testDateTime, 1L);
				assertEquals(expected, newsService.readNewsById(1L));
		}
		@SneakyThrows
		@Test
		void shouldThrowExceptionWhenThereIsNoNewsWithSpecificId() {
				assertThrows(NoSuchNewsException.class, () -> newsService.readNewsById(3L));
		}
		@SneakyThrows
		@Test
		void shouldReturnAddedObjectIfValuesAreCorrect() {
				NewsModelDTO expected = new NewsModelDTO(1L, "testTitle", "testContent", testDateTime, testDateTime, 1L);
				assertEquals(expected, newsService.createNewNews(expected));
		}
		@Test
		void shouldThrowExceptionIfValuesAreIncorrect() {
				NewsModelDTO expected = new NewsModelDTO(1L, "test", "test", testDateTime, testDateTime, 1L);
				assertThrows(InvalidNewsContentException.class, () -> newsService.createNewNews(expected));
		}
		@SneakyThrows
		@Test
		void shouldUpdateNewsWithGivenIdWhenValuesOfTitleAndContentAreCorrect() {
				NewsModelDTO expected = new NewsModelDTO(1L, "new_title", "new_content", testDateTime, testDateTime, 1L);
				assertEquals(expected, newsService.updateNews(expected));
		}
		@Test
		void shouldThrowExceptionWhenUpdatedValuesAreIncorrect() {
				NewsModelDTO expected = new NewsModelDTO(1L, "new", "new_content", testDateTime, testDateTime, 1L);
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