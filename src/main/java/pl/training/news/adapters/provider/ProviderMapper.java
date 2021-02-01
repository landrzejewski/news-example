package pl.training.news.adapters.provider;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.training.news.domain.Article;
import pl.training.news.domain.News;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class ProviderMapper {

    @Mapping(source = "publishedAt", target = "publicationDate")
    abstract Article toArticle(ArticleDto articleDto);

    abstract List<Article> toArticles(List<ArticleDto> articleDtos);

    News toNews(String country, String category, List<ArticleDto> articlesDtos) {
        return new News(country, category, toArticles(articlesDtos));
    }

}
