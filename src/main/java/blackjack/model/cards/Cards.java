package blackjack.model.cards;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BOUNDARY_SCORE = 11;
    private static final int EXTRA_SCORE = 10;
    private static final int WINNING_SCORE = 21;

    private final List<Card> cards = new ArrayList<>();
    private int score = 0;

    public void add(Card card) {
        cards.add(card);
        updateCardsScore();
    }

    public void add(List<Card> cardsToAdd) {
        cards.addAll(cardsToAdd);
        updateCardsScore();
    }

    public boolean isBust() {
        return score > WINNING_SCORE;
    }

    private void updateCardsScore() {
        score = calculate(cards);
        if (hasAce() && score <= BOUNDARY_SCORE) {
            score += EXTRA_SCORE;
        }
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculate(List<Card> cardsToAdd) {
        return cardsToAdd.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int getScore() {
        return score;
    }

    public List<Card> getCards() {
        return cards;
    }
}
