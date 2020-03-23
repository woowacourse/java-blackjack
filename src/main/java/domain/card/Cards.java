package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final String DUPLICATE_CARD_EXCEPTION_MESSAGE = "Duplicate card exception.";
    private static final String CARD_NULL_EXCEPTION_MESSAGE = "Card null exception.";
    private static final int BLACK_JACK_CARDS_AMOUNT = 2;

    private List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        validateCard(card);
        cards.add(card);
    }

    private void validateCard(Card card) {
        if (Objects.isNull(card)) {
            throw new NullPointerException(CARD_NULL_EXCEPTION_MESSAGE);
        }
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
    }

    public boolean isMoreThanScore(Cards cards) {
        return getScore().isMoreThan(cards.getScore());
    }

    public Score getScore() {
        int score = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        return new Score(score, countAce());
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean matchScore(Cards cards) {
        return this.getScore().equals(cards.getScore());
    }

    public boolean isBust() {
        return getScore().isBust();
    }

    public boolean isBlackJack() {
        return getScore().isBlackJackScore() && cards.size() == BLACK_JACK_CARDS_AMOUNT;
    }

    public boolean canHit(int maxHitScore) {
        return getScore().canHit(maxHitScore);
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        String string = cards.toString();
        return string.substring(1, string.length() - 1);
    }
}
