package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import java.util.Collections;
import java.util.List;

public class Player {

    private final Hand hand;

    public Player() {
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public int totalScore() {
        return hand.totalScore();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand.toList());
    }
}
