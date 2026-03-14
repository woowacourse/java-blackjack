package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Dealer {
    private static final int DEALER_HIT_THRESHOLD = 16;

    private final Hand hand;

    private Dealer(Hand hand) {
        validate(hand);
        this.hand = hand;
    }

    private void validate(Hand hand) {
        Objects.requireNonNull(hand, "딜러 덱(카드)는 null일 수 없습니다.");
    }

    public static Dealer of() {
        return new Dealer(Hand.init());
    }

    public void hit(TrumpCard card) {
        hand.add(card);
    }

    public int score() {
        return hand.calculateScore();
    }

    public List<TrumpCard> getCards() {
        return hand.getCards();
    }

    public boolean shouldHit() {
        return score() <= DEALER_HIT_THRESHOLD;
    }

    public boolean isBust() {
        return score() > 21;
    }

    public int countCards() {
        return hand.countCards();
    }
}
