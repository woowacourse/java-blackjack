package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class HoldingCards {
    private static final int FULL_SCORE = 21;
    private static final int BLACK_JACK_CARD_COUNT = 2;

    private final List<Card> cards;

    public HoldingCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return calculateTotal() > FULL_SCORE;
    }

    private boolean isBlackJack() {
        return calculateTotal() == FULL_SCORE && cards.size() == BLACK_JACK_CARD_COUNT;
    }

    public boolean isFullScoreOrBust() {
        return calculateTotal() == FULL_SCORE || isBust();
    }

    public int calculateTotal() {
        int sum = sum();
        if (hasAce() && sum <= 11) {
            sum += 10;
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int sum() {
        return cards.stream()
                .mapToInt(Card::getCardNumberValue)
                .sum();
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }
}
