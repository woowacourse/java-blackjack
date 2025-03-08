package domain.participant;

public interface ParticipantResult<T> {

    T get();

    void add(GameResult gameResult);
}
