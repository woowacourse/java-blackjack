package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int ACE_DIFF = 10;
    protected static final int BLACKJACK = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(Card firstCard, Card secondCard) {
        cards = new ArrayList<>(Arrays.asList(firstCard, secondCard));
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int score() {
        int lowScore = getLowScore();
        int highScore = getHighScore(lowScore);
        if (highScore > BLACKJACK) {
            return lowScore;
        }
        return highScore;
    }

    private int getLowScore() {
        return cards.stream()
            .mapToInt(Card::getCardValue)
            .sum();
    }

    private int getHighScore(int lowValue) {
        int highValue = lowValue;
        if (isContains(CardNumber.ACE)) {
            highValue += ACE_DIFF;
        }
        return highValue;
    }

    public boolean isContains(CardNumber number) {
        return cards.stream().anyMatch(card -> card.getNumber() == number);
    }

    public boolean isBlackjack() {
        return score() == BLACKJACK && cards.size() == BLACKJACK_CARD_SIZE;
    }

    public boolean isBust() {
        return score() > BLACKJACK;
    }
}
