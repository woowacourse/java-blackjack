package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BONUS_THRESHOLD = 11;
    private static final int ACE_BONUS = 10;
    private static final int BLACKJACK_COUNT = 2;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int sumWithAce() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (sum <= BONUS_THRESHOLD && hasA()) {
            sum += ACE_BONUS;
        }

        return sum;
    }

    private boolean hasA() {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    public Card getCardExceptHidden() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBlackJackCount() {
        return cards.size() == BLACKJACK_COUNT;
    }
}
