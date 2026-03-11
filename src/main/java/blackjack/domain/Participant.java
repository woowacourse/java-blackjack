package blackjack.domain;

import java.util.List;

public abstract class Participant {

    protected final Hand hand;
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

    public List<String> getCardNames() {
        return hand.getCardNames(0);
    }

    public abstract String getNickname();
}
