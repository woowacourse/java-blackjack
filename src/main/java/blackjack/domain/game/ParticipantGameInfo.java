package blackjack.domain.game;

import blackjack.domain.card.Cards;

public class ParticipantGameInfo {

    private final int score;
    private final Status status;

    public ParticipantGameInfo(Cards cards) {
        this.score = cards.sum();
        this.status = Status.findStatus(cards.getCount(), this.score);
    }

    public boolean isBlackJack() {
        return this.status == Status.BLACKJACK;
    }

    public int getScore() {
        return score;
    }

    public Status getStatus() {
        return status;
    }
}
