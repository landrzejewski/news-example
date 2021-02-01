package pl.training.news.domain;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

class CountriesService {

    void validate(String country) {
        if (getCountries().noneMatch(isoCountry -> isoCountry.equalsIgnoreCase(country))) {
            throw new IllegalArgumentException();
        }
    }

    private Stream<String> getCountries() {
        return Arrays.stream(Locale.getISOCountries());
    }

}
