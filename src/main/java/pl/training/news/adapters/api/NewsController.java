package pl.training.news.adapters.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.training.news.domain.NewsService;

@RestController
@RequiredArgsConstructor
final class NewsController {

    private final NewsService newsService;
    private final ApiMapper apiMapper;

    @GetMapping("news/{country}/{category}")
    NewsDto getNews(@PathVariable final String country, @PathVariable final String category) {
        var news = newsService.getNews(country, category);
        return apiMapper.toNewsDto(news);
    }

}
