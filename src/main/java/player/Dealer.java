package player;

import java.util.List;

import card.Card;

public class Dealer {
    private final Hand hand = new Hand();

    public void hit(Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public Card showOneCard() {
        return hand.pickFirstCard();
    }
}
