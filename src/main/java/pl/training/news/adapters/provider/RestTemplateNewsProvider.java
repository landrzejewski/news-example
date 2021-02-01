package pl.training.news.adapters.provider;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.training.news.domain.News;
import pl.training.news.domain.NewsProvider;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestTemplateNewsProvider implements NewsProvider {

    private final RestTemplate restTemplate;
    private final ProviderMapper providerMapper;

    @Value("${newsApi.key}")
    @Setter
    private String apiKey;
    @Value("${newsApi.url}")
    @Setter
    private String apiUrl;

    @Override
    public Optional<News> getNews(String country, String category) {
       var url = createUrl(country, category);
       return getNews(url)
               .map(newsDto -> providerMapper.toNews(country, category, newsDto.articles));
    }

    private String createUrl(String country, String category) {
        return String.format(apiUrl, country, category, apiKey);
    }

    private Optional<NewsDto> getNews(String url) {
        return Optional.ofNullable(restTemplate.getForObject(url, NewsDto.class));
    }

}
