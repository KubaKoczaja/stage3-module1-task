package com.mjc.school.service.mapper;

import com.mjc.school.repository.DTO.NewsDTO;
import com.mjc.school.repository.News;
import org.mapstruct.Mapper;

@Mapper
public interface NewsMapper {
		NewsDTO newsToNewsDTO(News news);
		News newsDTOToNews(NewsDTO newsDTO);
}
