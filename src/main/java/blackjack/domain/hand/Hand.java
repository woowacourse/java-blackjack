package blackjack.domain.hand;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int ACE_BONUS = 10;
    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards = new ArrayList<>();

    public void add(final Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        final int baseScore = sumCardValues();
        final Score scoreWithAce = new Score(baseScore + ACE_BONUS);
        if (shouldApplyAceBonus(scoreWithAce)) {
            return scoreWithAce;
        }
        return new Score(baseScore);
    }

    private boolean shouldApplyAceBonus(final Score score) {
        return hasAce() && !score.isBust();
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT &&
                calculateScore().getValue() == BLACKJACK_SCORE;
    }

    private int sumCardValues() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
