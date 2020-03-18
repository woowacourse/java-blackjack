package domain;

import util.BlackJackRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final String DUPLICATE_CARD_EXCEPTION_MESSAGE = "Duplicate card exception.";
    private static final String NULL_EXCEPTION_MESSAGE = "Null exception.";

    private List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        validateCard(card);
        cards.add(card);
    }

    private void validateCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
        if (Objects.isNull(card)) {
            throw new IllegalArgumentException(NULL_EXCEPTION_MESSAGE);
        }
    }

    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getValue();
        }
        return addAceWeight(score);
    }

    private int addAceWeight(int score) {
        for (Card card : cards) {
            if (BlackJackRule.isBust(score + Symbol.getAceWeight())) {
                break;
            }
            if (card.isAce()) {
                score += Symbol.getAceWeight();
            }
        }
        return score;
    }

    public boolean isBust() {
        return BlackJackRule.isBust(getScore());
    }

    public boolean isBlackJack() {
        return BlackJackRule.isBlackJack(getScore());
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
