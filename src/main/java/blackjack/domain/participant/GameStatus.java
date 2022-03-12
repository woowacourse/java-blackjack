package blackjack.domain.participant;

public enum GameStatus {

    RUNNING,
    BUST,
    BLACKJACK,
    FINISHED,
    ;

    public boolean isFinishedGame() {
        return this != RUNNING;
    }
}
