package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    public static final int MAX_SUM_OF_CARDS = 21;
    private static final int CARD_COUNT_OF_BLACKJACK = 2;
    private static final int ACE_NUMBER_DIFFERENCE = 10;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int sum() {
        int sum = cards.stream()
                .mapToInt(card -> card.number().getNumber())
                .sum();
        if (hasAceCard()
                && sum + ACE_NUMBER_DIFFERENCE <= MAX_SUM_OF_CARDS) {
            return sum + ACE_NUMBER_DIFFERENCE;
        }
        return sum;
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        if (cards.size() != CARD_COUNT_OF_BLACKJACK) {
            return false;
        }
        return sum() == MAX_SUM_OF_CARDS;
    }

    public boolean isBust() {
        return sum() > MAX_SUM_OF_CARDS;
    }

    public int count() {
        return cards.size();
    }
}

