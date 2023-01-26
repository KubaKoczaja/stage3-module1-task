package com.mjc.school.service;

import com.mjc.school.repository.NewsModelRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.implementation.NewsModelServiceImpl;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validator.InputValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewsModelServiceTest {
		@InjectMocks
		private NewsModelServiceImpl newsService;
		@Mock
		private NewsModelRepository newsModelRepository;
		@Mock
		private NewsMapper newsMapper;
		@Mock
		private InputValidator inputValidator;
		private final LocalDateTime testDateTime = LocalDateTime.of(2023, Month.JANUARY,10,12,15,10,0);

		@Test
		void shouldReturnAListOfAllNews() {
				List<NewsModel> newsModelList = List.of(new NewsModel(), new NewsModel());
				when(newsMapper.newsToNewsDTO(any(NewsModel.class))).thenReturn(new NewsModelDto());
				when(newsModelRepository.readAll()).thenReturn(newsModelList);
				int lengthExpected = 2;
				assertEquals(lengthExpected, newsService.readAllNews().size());
		}
		@Test
		void shouldReturnNewsDTOObjectWithGivenId() {
				NewsModelDto expected = new NewsModelDto(1L, "test", "test", LocalDateTime.now(), LocalDateTime.now(), 1L);
				when(inputValidator.validateIfNewsWithIdExists(anyLong(), anyList())).thenReturn(true);
				when(newsModelRepository.readById(anyLong())).thenReturn(new NewsModel());
				when(newsMapper.newsToNewsDTO(any(NewsModel.class))).thenReturn(expected);
				assertEquals(expected, newsService.readById(1L));
		}
		@Test
		void shouldThrowExceptionWhenThereIsNoNewsWithSpecificId() {
				when(inputValidator.validateIfNewsWithIdExists(anyLong(), anyList())).thenReturn(false);
				assertThrows(NoSuchNewsException.class, () -> newsService.readById(3L));
		}
		@Test
		void shouldReturnAddedObjectIfValuesAreCorrect() {
				NewsModelDto expected = new NewsModelDto(1L, "testTitle", "testContent", LocalDateTime.now(), LocalDateTime.now(), 1L);
				lenient().when(inputValidator.validateIfNewsWithIdExists(anyLong(), anyList())).thenReturn(true);
				when(inputValidator.validateProperLengthOfString(anyString(), anyInt(), anyInt())).thenReturn(true);
				lenient().when(newsMapper.newsDTOToNews(any(NewsModelDto.class))).thenReturn(new NewsModel());
				lenient().when(newsModelRepository.readById(anyLong())).thenReturn(new NewsModel());
				assertEquals(expected, newsService.createNewNews(expected));
		}
		@Test
		void shouldThrowExceptionIfValuesAreIncorrect() {
				NewsModelDto incorrectNewsModelDto = new NewsModelDto(1L, "incorrectData", "incorrectData", testDateTime, testDateTime, 1L);
				lenient().when(inputValidator.validateProperLengthOfString(anyString(), anyInt(), anyInt())).thenReturn(false);
				assertThrows(InvalidNewsContentException.class, () -> newsService.createNewNews(incorrectNewsModelDto));
		}
		@Test
		void shouldUpdateNewsWithGivenIdWhenValuesOfTitleAndContentAreCorrect() {
				NewsModelDto expected = new NewsModelDto(1L, "new_title", "new_content", testDateTime, testDateTime, 1L);
				lenient().when(inputValidator.validateIfNewsWithIdExists(anyLong(), anyList())).thenReturn(true);
				when(inputValidator.validateProperLengthOfString(anyString(), anyInt(), anyInt())).thenReturn(true);
				lenient().when(newsMapper.newsDTOToNews(any(NewsModelDto.class))).thenReturn(new NewsModel());
				lenient().when(newsModelRepository.readById(anyLong())).thenReturn(new NewsModel());
				assertEquals(expected, newsService.updateNews(expected));
		}

		@Test
		void shouldThrowExceptionWhenUpdatedValuesAreIncorrect() {
				NewsModelDto incorrectNewsModelDto = new NewsModelDto(1L, "incorrectData", "incorrectData", testDateTime, testDateTime, 1L);
				when(inputValidator.validateProperLengthOfString(anyString(), anyInt(), anyInt())).thenReturn(false);
				assertThrows(InvalidNewsContentException.class, () -> newsService.updateNews(incorrectNewsModelDto));
		}
		@Test
		void shouldReturnTrueIfNewsWasSuccessfullyRemoved() {
				when(inputValidator.validateIfNewsWithIdExists(anyLong(), anyList())).thenReturn(true);
				when(newsModelRepository.deleteById(anyLong())).thenReturn(true);
				assertTrue(newsService.deleteNewsById(1L));
		}
		@Test
		void shouldThrowExceptionWhenRemovingNonExistingNews() {
				when(inputValidator.validateIfNewsWithIdExists(anyLong(), anyList())).thenReturn(false);
				assertThrows(NoSuchNewsException.class, () -> newsService.deleteNewsById(3L));
		}
}