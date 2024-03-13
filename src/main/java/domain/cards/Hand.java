package domain.cards;

import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int BUST_THRESHOLD = 21;
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int FIRST_CARD_INDEX = 0;
    private static final int A_SCORE_GAP = 10;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = sumAllCards();
        return decideScore(score);
    }

    private int sumAllCards() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    private int decideScore(int score) {
        if (hasNotAce()) {
            return score;
        }
        if (score + A_SCORE_GAP <= BUST_THRESHOLD) {
            return score + A_SCORE_GAP;
        }
        return score;
    }

    private boolean hasNotAce() {
        return cards.stream().noneMatch(Card::isAce);
    }

    public boolean hasScoreUnderBustThreshold() {
        return calculateScore() <= BUST_THRESHOLD;
    }

    public boolean hasScoreUnderHitThreshold() {
        return calculateScore() <= DEALER_HIT_THRESHOLD;
    }

    public Card pickFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }

    public boolean hasSize(int size) {
        return cards.size() == size;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
