package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.NewsModelDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-12T11:22:20+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsModelDto newsToNewsDTO(NewsModel newsModel) {
        if ( newsModel == null ) {
            return null;
        }

        NewsModelDto newsModelDto = new NewsModelDto();

        newsModelDto.setId( newsModel.getId() );
        newsModelDto.setTitle( newsModel.getTitle() );
        newsModelDto.setContent( newsModel.getContent() );
        newsModelDto.setCreateDate( newsModel.getCreateDate() );
        newsModelDto.setLastUpdateDate( newsModel.getLastUpdateDate() );
        newsModelDto.setAuthorId( newsModel.getAuthorId() );

        return newsModelDto;
    }

    @Override
    public NewsModel newsDTOToNews(NewsModelDto newsModelDTO) {
        if ( newsModelDTO == null ) {
            return null;
        }

        NewsModel newsModel = new NewsModel();

        newsModel.setId( newsModelDTO.getId() );
        newsModel.setTitle( newsModelDTO.getTitle() );
        newsModel.setContent( newsModelDTO.getContent() );
        newsModel.setCreateDate( newsModelDTO.getCreateDate() );
        newsModel.setLastUpdateDate( newsModelDTO.getLastUpdateDate() );
        newsModel.setAuthorId( newsModelDTO.getAuthorId() );

        return newsModel;
    }
}
