package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;

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

}
