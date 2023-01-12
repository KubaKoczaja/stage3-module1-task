package com.mjc.school.service.validator;

import com.mjc.school.repository.model.dto.NewsModelDto;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class InputValidator {
		public boolean validateIfNewsWithIdExists(Long id, List<NewsModelDto> list) {
				return list.stream().map(NewsModelDto::getId).anyMatch(i -> i.equals(id));
		}
		public boolean validateProperLengthOfString(String string, int min, int max) {
				return string.length() >= min && string.length() <= max;
		}
}
