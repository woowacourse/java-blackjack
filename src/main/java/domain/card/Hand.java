package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int FIRST_CARD_INDEX = 0;
    private static final int BUST_THRESHOLD = 21;
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int A_SCORE_GAP = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = sumAllCards();
        if (hasAce()) {
            score = decideScore(score);
        }
        return score;
    }

    private int sumAllCards() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private int decideScore(int score) {
        if (cannotBust(score + A_SCORE_GAP)) {
            return score + A_SCORE_GAP;
        }
        return score;
    }

    public boolean cannotBust() {
        return calculateScore() <= BUST_THRESHOLD;
    }

    public boolean cannotBust(int score) {
        return score <= BUST_THRESHOLD;
    }

    public boolean cannotDealerHit() {
        return calculateScore() <= DEALER_HIT_THRESHOLD;
    }

    public boolean hasScore(int score) {
        return calculateScore() == score;
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
