package blackjack.domain;

import java.util.List;

public abstract class Participant {

    private final Hand hand;
    private Status status;

    public Participant(final Hand hand, final Status status) {
        this.hand = hand;
        this.status = status;
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public Status getStatus() {
        return status;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public boolean isHit() {
        return status == Status.HIT;
    }

    public boolean isBurst() {
        return status == Status.BURST;
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public void stay() {
        if (status == Status.HIT) {
            status = Status.STAY;
        }
    }

    public void handleBurst() {
        if (hand.isBurst()) {
            status = Status.BURST;
        }
    }

    public void handleBlackjack() {
        if (isBlackjack()) {
            status = Status.STAY;
        }
    }

    public List<String> getCardNames() {
        return hand.getCardNames(0);
    }

    protected List<String> getCardNames(int startInclusive) {
        return hand.getCardNames(startInclusive);
    }

    public abstract Name getName();
}
