package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public enum ParticipantStatus {

    RUNNING,
    BUST,
    BLACKJACK,
    FINISHED,
    ;

    public boolean isFinishedGame() {
        return this != RUNNING;
    }

    public ParticipantStatus refreshStatus(final Cards cards) {
        if (cards.isBust()) {
            return BUST;
        }
        if (cards.isBlackJack()) {
            return BLACKJACK;
        }
        return this;
    }
}
