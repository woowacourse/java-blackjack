package domain.participant;

import constant.PolicyConstant;
import domain.Card;
import domain.Hand;
import java.util.List;

public class Participant {

    protected final Name name;
    protected final Hand hand;

    public Participant(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name.value();
    }

    public Hand getHand() {
        return hand;
    }

    public void addCard(List<Card> cards) {
        for (Card card : cards) {
            hand.addCard(card);
        }
    }

    public boolean isBust() {
        return hand.calculateScore() > PolicyConstant.BLACKJACK_SCORE;
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public boolean isBlackjack() {
        return hand.calculateScore() == PolicyConstant.BLACKJACK_SCORE && hand.getCardNames().size() == 2;
    }
}
