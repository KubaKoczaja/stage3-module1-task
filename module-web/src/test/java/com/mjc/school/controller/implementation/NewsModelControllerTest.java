package com.mjc.school.controller.implementation;

import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidNewsContentException;
import com.mjc.school.service.exception.NoSuchNewsException;
import com.mjc.school.service.implementation.NewsModelServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewsModelControllerTest {

		@Mock
		private NewsModelServiceImpl newsModelService;

		@InjectMocks
		private NewsModelControllerImpl newsModelController;

		@Test
		void shouldReadAllNews() {
				List<NewsModelDto> list = List.of(new NewsModelDto(1L,"test","test", LocalDateTime.now(), LocalDateTime.now(), 1L),new NewsModelDto(1L,"test","test", LocalDateTime.now(), LocalDateTime.now(), 1L));
				when(newsModelService.readAllNews()).thenReturn(list);
				assertEquals(2, newsModelController.readAllNewsRequest().size());
		}
		@Test
		void shouldReturnNewsWithValidId() {
				NewsModelDto expectedNewsModelDto = new NewsModelDto(1L,"test","test", LocalDateTime.now(), LocalDateTime.now(), 1L);
				when(newsModelService.readById(anyLong())).thenReturn(expectedNewsModelDto);
				assertEquals(expectedNewsModelDto, newsModelController.readByIdRequest(1L));
		}
		@Test
		void shouldThrowExceptionWhenIdIsInvalid() {
				when(newsModelService.readById(anyLong())).thenThrow(NoSuchNewsException.class);
				assertThrows(NoSuchNewsException.class, () -> newsModelController.readByIdRequest(1L));
		}
		@Test
		void shouldCreateNewsRequestWithProperInput() {
				NewsModelDto expectedNewsModelDto = new NewsModelDto(1L,"test","test", LocalDateTime.now(), LocalDateTime.now(), 1L);
				when(newsModelService.createNewNews(any(NewsModelDto.class))).thenReturn(expectedNewsModelDto);
				assertEquals(expectedNewsModelDto, newsModelController.createNewsRequest(expectedNewsModelDto));
		}
		@Test
		void shouldThrowExceptionWhenInputForCreatingNewNewsIsIncorrect() {
				NewsModelDto incorrectNewsModelDto = new NewsModelDto(1L,"incorrectData","incorrectData", LocalDateTime.now(), LocalDateTime.now(), 1L);
				when(newsModelService.createNewNews(any(NewsModelDto.class))).thenThrow(InvalidNewsContentException.class);
				assertThrows(InvalidNewsContentException.class, () -> newsModelController.createNewsRequest(incorrectNewsModelDto));
		}
		@Test
		void shouldUpdateNewsRequestWhenIdIsCorrect() {
				NewsModelDto expectedNewsModelDto = new NewsModelDto(1L,"test","test", LocalDateTime.now(), LocalDateTime.now(), 1L);
				when(newsModelService.readById(anyLong())).thenReturn(expectedNewsModelDto);
				when(newsModelService.updateNews(any(NewsModelDto.class))).thenReturn(expectedNewsModelDto);
				assertEquals(expectedNewsModelDto, newsModelController.updateNewsRequest(expectedNewsModelDto));
		}
		@Test
		void shouldThrowExceptionWhenNewsToUpdateIdIsIncorrect() {
				NewsModelDto incorrectNewsModelDto = new NewsModelDto(1L,"incorrectData","incorrectData", LocalDateTime.now(), LocalDateTime.now(), 1L);
				when(newsModelService.readById(anyLong())).thenThrow(NoSuchNewsException.class);
				assertThrows(NoSuchNewsException.class, () -> newsModelController.updateNewsRequest(incorrectNewsModelDto));
		}
		@Test
		void shouldDeleteNewsWithGivenCorrectId() {
				when(newsModelService.deleteNewsById(anyLong())).thenReturn(true);
				assertTrue(newsModelController.deleteByIdRequest(1L));
		}
		@Test
		void shouldThrowExceptionDuringDeletingWhenIdIsIncorrect() {
				when(newsModelService.deleteNewsById(anyLong())).thenThrow(NoSuchNewsException.class);
				assertThrows(NoSuchNewsException.class, () -> newsModelController.deleteByIdRequest(1L));
		}
}