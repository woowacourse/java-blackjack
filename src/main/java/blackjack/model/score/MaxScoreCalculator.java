package blackjack.model.score;

import blackjack.model.card.Card;
import blackjack.model.player.Cards;

public final class MaxScoreCalculator {

    public Score calculate(Cards cards) {
        return new Score(softHandScore(cards));
    }

    private int softHandScore(Cards cards) {
        return cards.values().stream()
            .mapToInt(Card::softRank)
            .sum();
    }
}
