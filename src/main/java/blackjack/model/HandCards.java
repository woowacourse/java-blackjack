package blackjack.model;

import blackjack.exception.ErrorMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandCards {

    public static final int BLACKJACK_CARD_COUNT = 2;
    public static final int BLACKJACK_SCORE = 21;
    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score calculate() {
        long aceCount = cards.stream()
                .filter(card -> Number.ACE.equals(card.getNumber()))
                .count();

        int total = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();

        return new Score(aceTranslate(total, aceCount));
    }

    public boolean isInitialCardSize() {
        return cards.size() == BLACKJACK_CARD_COUNT;
    }

    public Card getFirstCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.CARD_EMPTY.getMessage());
        }
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private static int aceTranslate(int score, long aceCount) {
        while (score <= 11 && aceCount > 0) {
            score += 10;
            aceCount--;
        }
        return score;
    }
}
