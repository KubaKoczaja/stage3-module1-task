package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.dto.NewsDTO;
import com.mjc.school.repository.model.News;
import org.mapstruct.Mapper;

@Mapper
public interface NewsMapper {
		NewsDTO newsToNewsDTO(News news);
		News newsDTOToNews(NewsDTO newsDTO);
}
