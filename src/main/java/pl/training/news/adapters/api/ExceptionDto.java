package pl.training.news.adapters.api;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
final class ExceptionDto {

    @NonNull
    String description;

}
