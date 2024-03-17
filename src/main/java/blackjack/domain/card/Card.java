package blackjack.domain.card;

import blackjack.domain.participant.Score;

public record Card(CardRank cardRank, CardSuit cardSuit) {

    public boolean isAce() {
        return cardRank.isAce();
    }

    public Score score() {
        return new Score(cardRank.getScore());
    }
}
