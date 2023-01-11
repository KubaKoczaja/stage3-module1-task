package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.NewsModelDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-10T11:17:08+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsModelDTO newsToNewsDTO(NewsModel newsModel) {
        if ( newsModel == null ) {
            return null;
        }

        NewsModelDTO newsModelDTO = new NewsModelDTO();

        newsModelDTO.setId( newsModel.getId() );
        newsModelDTO.setTitle( newsModel.getTitle() );
        newsModelDTO.setContent( newsModel.getContent() );
        newsModelDTO.setCreateDate( newsModel.getCreateDate() );
        newsModelDTO.setLastUpdateDate( newsModel.getLastUpdateDate() );
        newsModelDTO.setAuthorId( newsModel.getAuthorId() );

        return newsModelDTO;
    }

    @Override
    public NewsModel newsDTOToNews(NewsModelDTO newsModelDTO) {
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
