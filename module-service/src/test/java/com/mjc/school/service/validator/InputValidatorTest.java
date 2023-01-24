package com.mjc.school.service.validator;

import com.mjc.school.repository.model.dto.NewsModelDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
		private final InputValidator inputValidator = new InputValidator();
		private final List<NewsModelDto> testList = List.of(new NewsModelDto(1L, "test", "test", LocalDateTime.now(), LocalDateTime.now(), 1L),
						new NewsModelDto(2L, "test", "test", LocalDateTime.now(), LocalDateTime.now(), 1L));
		private final static int MIN = 5;
		private final static int MAX = 10;

		@Test
		void shouldReturnFalseIfNewsWithSpecificIdDoesNotExists() {
				Long newsId = 3L;
				assertFalse(inputValidator.validateIfNewsWithIdExists(newsId, testList));
		}
		@Test
		void shouldReturnTrueIfNewsWithSpecificIdExists() {
				Long newsId = 1L;
				assertTrue(inputValidator.validateIfNewsWithIdExists(newsId, testList));
		}
		@Test
		void shouldReturnFalseIfLengthIsIncorrect() {
				String testString = "test";
				assertFalse(inputValidator.validateProperLengthOfString(testString, MIN, MAX));
		}
		@Test
		void shouldReturnTrueIfLengthIsCorrect() {
				String testString = "test_test";
				assertTrue(inputValidator.validateProperLengthOfString(testString, MIN, MAX));
		}
}