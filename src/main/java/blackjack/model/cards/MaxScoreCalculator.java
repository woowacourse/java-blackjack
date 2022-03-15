package blackjack.model.cards;

import blackjack.model.card.Card;

public final class MaxScoreCalculator {

    public Score score(Cards cards) {
        return new Score(softHandScore(cards));
    }

    private int softHandScore(Cards cards) {
        return cards.values().stream()
            .mapToInt(Card::softRank)
            .sum();
    }
}
