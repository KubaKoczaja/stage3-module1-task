package com.mjc.school.service.validator;

import com.mjc.school.repository.model.dto.NewsModelDTO;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class InputValidator {
		public boolean validateIfNewsWithIdExists(Long id, List<NewsModelDTO> list) {
				return list.stream().map(NewsModelDTO::getId).anyMatch(i -> i.equals(id));
		}
		public boolean validateProperLengthOfString(String string, int min, int max) {
				return string.length() >= min && string.length() <= max;
		}
}
