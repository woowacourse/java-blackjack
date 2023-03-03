package player;

import java.util.List;

import card.Card;

public class Dealer {
    public static final String DEALER_NAME = "딜러";
    private final Name name;
    private final Hand hand = new Hand();

    public Dealer() {
        this.name = new Name(DEALER_NAME);
    }

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

    public boolean isUnderScore() {
        return hand.calculateScore() <= 16;
    }

    public boolean isBust() {
        return hand.calculateScore() >= 22;
    }

    public Name getName() {
        return name;
    }
}
