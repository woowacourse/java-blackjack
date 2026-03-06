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

    public void stay() {
        if (status == Status.HIT) {
            status = Status.STAY;
        }
    }

    public void burst() {
        if (status == Status.HIT) {
            status = Status.STAY;
        }
    }

    public abstract List<String> getCardNames();
}
