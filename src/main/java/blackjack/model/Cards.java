package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BOUNDARY_SCORE = 11;
    private static final int EXTRA_SCORE = 10;
    private static final int WINNING_SCORE = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateScore() {
        int score = calculate();
        if (hasAce() && score <= BOUNDARY_SCORE) {
            return score + EXTRA_SCORE;
        }
        return score;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isGreaterThanWinningScore() {
        return calculateScore() > WINNING_SCORE;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculate() {
        return cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return cards;
    }
}
