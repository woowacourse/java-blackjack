package blackjack.domain.hand;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int ACE_BONUS = 10;

    private final List<Card> cards = new ArrayList<>();

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Score calculateScore() {
        final int baseScore = sumCardValues();
        final Score scoreWithAce = new Score(baseScore + ACE_BONUS);
        if (shouldApplyAceBonus(scoreWithAce)) {
            return scoreWithAce;
        }
        return new Score(baseScore);
    }

    private int sumCardValues() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private boolean shouldApplyAceBonus(Score score) {
        return hasAce() && !score.isBust();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }
}
