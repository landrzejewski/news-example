package pl.training.news.adapters.api;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.training.news.domain.News;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ApiMapper {

   NewsDto toNewsDto(News news);

}
