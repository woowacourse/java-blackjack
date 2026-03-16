package blackjack.domain;

import java.util.List;

public abstract class Participant {
    private static final int BUST_SCORE = 21;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final Cards drawnCards;

    public Participant() {
        this.drawnCards = new Cards();
    }

    public void receiveOneCard(Card card) {
        drawnCards.addCard(card);
    }

    public List<String> getCardNames() {
        return drawnCards.getCardNames();
    }

    public boolean isBust() {
        return drawnCards.sumScore() > BUST_SCORE;
    }

    public int calculateTotalScore() {
        return drawnCards.sumScore();
    }

    public boolean isBlackjack() {
        if (drawnCards.getCardCount() != BLACKJACK_CARD_COUNT) {
            return false;
        }
        return drawnCards.sumScore() == BLACKJACK_SCORE;
    }
}
