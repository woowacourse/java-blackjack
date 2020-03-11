package user;

import card.Deck;

public class Dealer implements User {
    public static final int DEALER_HIT_POINT = 16;

    private Hands hands;

    public Dealer(Deck deck) {
        this.hands = new Hands(deck);
    }

    public Dealer(Hands hands) {
        this.hands = hands;
    }

    @Override
    public void hit(Deck deck) {
        if (this.hands.score() <= DEALER_HIT_POINT) {
            this.hands.draw(deck);
        }
    }

    @Override
    public boolean checkBurst() {
        return hands.isBurst();
    }

    @Override
    public int handSize() {
        return hands.size();
    }
}
