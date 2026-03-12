package domain.participant;

import domain.Card;
import domain.Hand;

import java.util.List;

public abstract class Participant {
    private static final int BLACKJACK_SCORE = 21;

    private final String name;
    protected final Hand hand;

    protected Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public abstract boolean canReceiveCard();

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.hasOnlyTwoCards() && hand.calculateScore() == BLACKJACK_SCORE;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public List<String> showHand() {
        return hand.showHand();
    }
}
