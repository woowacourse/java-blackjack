package blackjack.model.cards;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    public static final int INITIAL_CARD_SIZE = 2;
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

    public boolean isBlackJack() {
        return cards.size() == INITIAL_CARD_SIZE && score == WINNING_SCORE;
    }

    private void updateCardsScore() {
        score = calculateScore(cards);
        if (hasAce() && lessThanWinningScoreWithExtraScore()) {
            score += EXTRA_SCORE;
        }
    }

    private boolean lessThanWinningScoreWithExtraScore() {
        return score + EXTRA_SCORE <= WINNING_SCORE;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateScore(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
