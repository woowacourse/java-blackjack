package blackjack.domain;

import java.util.List;

public abstract class Participant {

    protected final Hand hand;
    private Status status;

    public Participant(final Hand hand, final Status status) {
        this.hand = hand;
        this.status = status;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public boolean isHit() {
        return status == Status.HIT;
    }

    public void stay() {
        if (status == Status.HIT) {
            status = Status.STAY;
        }
    }

    public void handleBurst() {
        if (hand.isBurst() && status == Status.HIT) {
            status = Status.BURST;
        }
    }

    public abstract List<String> getCardNames();
}
