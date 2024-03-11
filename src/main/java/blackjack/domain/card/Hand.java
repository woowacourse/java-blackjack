package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_SIZE = 2;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int totalMinScore = getMinScore();
        int biggerScore = getBiggerScore();

        if (biggerScore > BLACKJACK_SCORE) {
            return totalMinScore;
        }
        return biggerScore;
    }

    private int getMinScore() {
        return cards.stream()
                .mapToInt(Card::getMinScore)
                .sum();
    }

    private int getBiggerScore() {
        int score = getMinScore();
        int differenceScore = cards.stream()
                .filter(Card::isAce)
                .mapToInt(this::calculateDifferenceScore)
                .findAny()
                .orElse(0);
        return score + differenceScore;
    }

    private int calculateDifferenceScore(Card card) {
        return card.getMaxScore() - card.getMinScore();
    }

    public boolean isBusted() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_SIZE && calculateScore() == BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
