package blackjack.model.cards;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;

public final class BestScoreCalculator {

    public Score score(Cards cards) {
        if (hasAce(cards) && !softHandScore(cards).isBust()) {
            return softHandScore(cards);
        }
        return hardHandScore(cards);
    }

    private boolean hasAce(Cards cards) {
        return cards.values().stream().anyMatch(Card::isAce);
    }

    private Score softHandScore(Cards cards) {
        return hardHandScore(cards).plus(increaseScore());
    }

    private Score increaseScore() {
        return new Score(Rank.ACE.soft() - Rank.ACE.hard());
    }

    private Score hardHandScore(Cards cards) {
        int score = cards.values().stream()
            .mapToInt(Card::hardRank)
            .sum();
        return new Score(score);
    }
}
