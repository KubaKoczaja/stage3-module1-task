package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.NewsModelDTO;
import org.mapstruct.Mapper;

@Mapper
public interface NewsMapper {
		NewsModelDTO newsToNewsDTO(NewsModel newsModel);
		NewsModel newsDTOToNews(NewsModelDTO newsModelDTO);
}
