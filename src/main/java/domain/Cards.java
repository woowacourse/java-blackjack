package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Cards of(List<Card> cards) {
        return new Cards(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int calculateScore() {
        int cardScore = calculateRawScore();
        int aceCount = countAce();

        for (int i = 0; i < aceCount; i++) {
            cardScore = adjustForAce(cardScore);
        }
        return cardScore;
    }

    private int calculateRawScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private int adjustForAce(int cardScore) {
        if (isBust(cardScore)) {
            cardScore -= Policy.ACE_HIGH_LOW_DIFF;
        }
        return cardScore;
    }

    public boolean isBust(int score) {
        return score > Policy.BLACKJACK_NUMBER;
    }

    public int getScoreOrZeroIfBust() {
        int score = calculateScore();
        if (isBust(score)) {
            return Policy.BUST_SCORE;
        }
        return score;
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public Card removeFirst() {
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
}
