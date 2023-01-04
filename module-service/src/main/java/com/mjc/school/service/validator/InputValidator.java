package com.mjc.school.service.validator;

import com.mjc.school.repository.model.dto.NewsModelDTO;

import java.util.List;

public class InputValidator {
		private InputValidator() {}
		public static boolean validateIfNewsWithIdExists(Long id, List<NewsModelDTO> list) {
				return list.stream().map(NewsModelDTO::getId).anyMatch(i -> i.equals(id));
		}
		public static boolean validateProperLengthOfString(String string, int min, int max) {
				return string.length() >= min && string.length() <= max;
		}
}
