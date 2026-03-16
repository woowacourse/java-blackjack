package blackjack.domain;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected static final int BLACKJACK_THRESHOLD = 21;
    protected static final int INITIAL_CARD_COUNT = 2;

    protected final Hand hand;

    protected Participant(Hand hand) {
        validateHandNotNull(hand);
        this.hand = hand;
    }

    private void validateHandNotNull(Hand hand) {
        Objects.requireNonNull(hand, "hand 은 null 이 올 수 없습니다.");
    }

    public int score() {
        return hand.calculateScore();
    }

    public List<TrumpCard> getCards() {
        return hand.getCards();
    }

    public int countCards() {
        return hand.countCards();
    }

    public boolean isBust() {
        return score() > BLACKJACK_THRESHOLD;
    }

    public void receiveCards(List<TrumpCard> cards) {
        for (TrumpCard card : cards) {
            hand.receive(card);
        }
    }

    public void receiveCard(TrumpCard card) {
        hand.receive(card);
    }

    public boolean isBlackjack() {
        return countCards() == INITIAL_CARD_COUNT && score() == BLACKJACK_THRESHOLD;
    }

}