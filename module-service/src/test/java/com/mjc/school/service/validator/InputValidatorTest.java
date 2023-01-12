package com.mjc.school.service.validator;

import com.mjc.school.repository.model.dto.NewsModelDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
		private final InputValidator inputValidator = new InputValidator();
		private final List<NewsModelDTO> testList = List.of(new NewsModelDTO(1L, "test", "test", LocalDateTime.now(), LocalDateTime.now(), 1L),
						new NewsModelDTO(2L, "test", "test", LocalDateTime.now(), LocalDateTime.now(), 1L));
		private final int min = 5;
		private final int max = 10;
		private Long newsId;
		private String testString;


		@Test
		void shouldReturnFalseIfNewsWithSpecificIdDoesNotExists() {
				newsId = 3L;
				assertFalse(inputValidator.validateIfNewsWithIdExists(newsId, testList));
		}
		@Test
		void shouldReturnTrueIfNewsWithSpecificIdExists() {
				newsId = 1L;
				assertTrue(inputValidator.validateIfNewsWithIdExists(newsId, testList));
		}
		@Test
		void shouldReturnFalseIfLengthIsIncorrect() {
				testString = "test";
				assertFalse(inputValidator.validateProperLengthOfString(testString, min, max));
		}
		@Test
		void shouldReturnTrueIfLengthIsCorrect() {
				testString = "test_test";
				assertTrue(inputValidator.validateProperLengthOfString(testString, min, max));
		}
}