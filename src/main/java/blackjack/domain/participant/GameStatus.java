package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public enum GameStatus {

    RUNNING,
    BUST,
    BLACKJACK,
    FINISHED,
    ;

    public boolean isFinishedGame() {
        return this != RUNNING;
    }

    public GameStatus refreshStatus(final Cards cards) {
        if (cards.isBust()) {
            return BUST;
        }
        if (cards.isBlackJack()) {
            return BLACKJACK;
        }
        return this;
    }
}
