package blackjack.model;

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
        score += card.getScore();
    }

    public void add(List<Card> cardsToAdd) {
        cards.addAll(cardsToAdd);
        score += calculate(cardsToAdd);
    }


    public boolean isGreaterThanWinningScore() {
        return score > WINNING_SCORE;
    }

    public int getCardsScore() {
        if (hasAce() && score <= BOUNDARY_SCORE) {
            return score + EXTRA_SCORE;
        }
        return score;
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

    public List<Card> getCards() {
        return cards;
    }
}
