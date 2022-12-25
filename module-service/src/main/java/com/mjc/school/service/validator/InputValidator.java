package com.mjc.school.service.validator;

import com.mjc.school.repository.DTO.NewsDTO;

import java.util.List;

public class InputValidator {
		public static boolean validateIfNewsWithIdExists(Long id, List<NewsDTO> list) {
				return list.stream().map(NewsDTO::getId).anyMatch(i -> i.equals(id));
		}
		public static boolean validateProperLengthOfString(String string, int min, int max) {
				return string.length() >= min && string.length() <= max;
		}
}
