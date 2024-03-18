package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_CARD_COUNT = 2;

    private final List<Card> cards;
    private Score score;

    public Hand(List<Card> cards) {
        this.cards = cards;
        this.score = new Score(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBust() {
        return this.score.getScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjackScore() {
        return this.score.getScore() == BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return isBlackjackScore() && cards.size() == INITIAL_CARD_COUNT;
    }

    public boolean isTotalScoreGreaterThan(int score) {
        return this.score.getScore() > score;
    }

    public void addCard(Card card) {
        cards.add(card);
        this.score = new Score(cards);
    }

    public int getScore() {
        return this.score.getScore();
    }
}
