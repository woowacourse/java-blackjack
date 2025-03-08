package domain;

public interface ParticipantResult<T> {

    T get();

    void add(GameResult gameResult);
}
