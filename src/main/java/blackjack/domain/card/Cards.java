package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    public static final int BLACK_JACK = 21;
    private static final int INITIAL_CARDS_SIZE = 2;
    private static final int ACE_EXTRA_VALUE = 10;
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        if (cards.size() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException();
        }
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getUnmodifiableList() {
        return Collections.unmodifiableList(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int sumCards() {
        return cards.stream().mapToInt(Card::getScore).sum();
    }

    public int sumCardsForResult() {
        int aceCount = (int) cards.stream().filter(Card::isAce).count();
        int sum = sumCards() + aceCount * ACE_EXTRA_VALUE;
        while (sum > BLACK_JACK && aceCount > 0) {
            sum -= ACE_EXTRA_VALUE;
            aceCount--;
        }
        return sum;
    }

}