package domain.card;

import static domain.BlackjackGame.BLACKJACK_SCORE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    private Hand() {
        this.cards = new ArrayList<>();
    }

    public static Hand of() {
        return new Hand();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = cards.stream()
                .mapToInt(Card::getMinScore)
                .sum();

        List<Card> scoreGapCards = cards.stream()
                .filter(Card::hasScoreGap)
                .toList();

        return scoreGapCards.stream()
                .reduce(sum, (acc, card) -> addScoreIfPossible(acc, card.getScoreGap()), Integer::sum);
    }

    private int addScoreIfPossible(int currentSum, int scoreGap) {
        if (currentSum + scoreGap > BLACKJACK_SCORE) {
            return currentSum;
        }
        return currentSum + scoreGap;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
