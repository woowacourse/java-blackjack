package blackjack.domain.participants;


import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;

public class Player {
    private final Name name;
    private final Hand hand;

    public Player(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public int calculateScore() {
        return hand.calculateTotalScore();
    }

    public boolean isNotOver(int boundaryScore) {
        return hand.calculateTotalScore() < boundaryScore;
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
