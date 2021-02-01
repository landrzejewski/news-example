package pl.training.news.domain;

public interface EventEmitter<E> {

    void emit(E event);

}
