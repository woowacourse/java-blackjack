package blackjack.domain.hand;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_BONUS = 10;

    private final List<Card> cards = new ArrayList<>();

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        if (hasAce() && score + ACE_BONUS <= BLACKJACK_SCORE) {
            return score + ACE_BONUS;
        }
        return score;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
