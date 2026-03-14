package domain;

import domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    private static final int HIT_LIMIT = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isBurst() {
        return hand.isBurst();
    }

    @Override
    public boolean canHit() {
        return hand.getSum() < HIT_LIMIT;
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public int getHandSize() {
        return hand.getSize();
    }

    public int getScore() {
        return hand.getSum();
    }
}
