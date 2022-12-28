package com.mjc.school.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class News {
		private Long id;
		private String title;
		private String content;
		private LocalDateTime createDate;
		private LocalDateTime lastUpdateDate;
		private Long authorId;
}
