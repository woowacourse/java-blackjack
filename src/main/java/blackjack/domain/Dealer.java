package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Dealer {
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int BLACKJACK_THRESHOLD = 21;

    private final Hand hand;

    private Dealer(Hand hand) {
        validate(hand);
        this.hand = hand;
    }

    public static Dealer of() {
        return new Dealer(Hand.init());
    }

    private void validate(Hand hand) {
        Objects.requireNonNull(hand, "hand 는 null 이 올 수 없습니다.");
    }

    public void receiveCards(List<TrumpCard> cards) {
        for (TrumpCard card : cards) {
            hand.receive(card);
        }
    }

    public void receive(TrumpCard card) {
        hand.receive(card);
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

    public TrumpCard getOpenCard() {
        return hand.getCards().getFirst();
    }

    public boolean isBust() {
        return score() > BLACKJACK_THRESHOLD;
    }
}