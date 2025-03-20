package model.hand;

import java.util.List;
import model.deck.Card;
import model.deck.CardRank;

public record Score(int score) {
    private static final int MAX_SCORE = 21;

    public static Score calculateDefault(List<Card> cards) {
        return new Score(cards.stream()
                .mapToInt(Card::getCardRankDefaultValue)
                .sum()
        );
    }

    public Score calculateMax() {
        return new Score(score - CardRank.ACE.getDefaultValue() + CardRank.ACE.getMaxValue());
    }

    public boolean isBurst() {
        return score > MAX_SCORE;
    }

    public boolean isBlackjack() {
        return score == MAX_SCORE;
    }

    public boolean isLessThan(Score bound) {
        return this.score < bound.score;
    }

    public boolean isSame(Score bound) {
        return this.score == bound.score;
    }

    public boolean isBiggerThan(Score bound) {
        return this.score > bound.score;
    }
}
