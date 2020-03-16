package domains.user;

import domains.card.Deck;

public class Dealer extends User {
    public static final int DEALER_HIT_POINT = 16;

    public Dealer(Deck deck) {
        this.hands = new Hands(deck);
    }

    public Dealer(Hands hands) {
        this.hands = hands;
    }

    public boolean isHit() {
        return this.hands.size() == 3;
    }

    @Override
    public void hit(Deck deck) {
        if (this.hands.score() <= DEALER_HIT_POINT) {
            this.hands.draw(deck);
        }
        if (hands.isBurst()) {
            this.burst = true;
        }
    }
}
