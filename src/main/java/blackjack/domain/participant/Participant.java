package blackjack.domain.participant;

import blackjack.domain.trump.Card;
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

    public void addCard(final Card card) {
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

    public List<String> getAllCardNames() {
        final int startIndex = 0;
        return hand.getCardNames(startIndex);
    }

    public abstract String getNickname();
}
