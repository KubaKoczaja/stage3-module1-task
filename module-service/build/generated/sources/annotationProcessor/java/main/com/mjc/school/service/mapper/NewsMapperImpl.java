package com.mjc.school.service.mapper;

import com.mjc.school.repository.DTO.NewsDTO;
import com.mjc.school.repository.News;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-25T18:01:16+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDTO newsToNewsDTO(News news) {
        if ( news == null ) {
            return null;
        }

        NewsDTO newsDTO = new NewsDTO();

        newsDTO.setId( news.getId() );
        newsDTO.setTitle( news.getTitle() );
        newsDTO.setContent( news.getContent() );
        newsDTO.setCreateDate( news.getCreateDate() );
        newsDTO.setLastUpdateDate( news.getLastUpdateDate() );
        newsDTO.setAuthorId( news.getAuthorId() );

        return newsDTO;
    }

    @Override
    public News newsDTOToNews(NewsDTO newsDTO) {
        if ( newsDTO == null ) {
            return null;
        }

        News news = new News();

        news.setId( newsDTO.getId() );
        news.setTitle( newsDTO.getTitle() );
        news.setContent( newsDTO.getContent() );
        news.setCreateDate( newsDTO.getCreateDate() );
        news.setLastUpdateDate( newsDTO.getLastUpdateDate() );
        news.setAuthorId( newsDTO.getAuthorId() );

        return news;
    }
}
