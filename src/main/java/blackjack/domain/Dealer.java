package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.ArrayList;

public class Dealer implements Gamer {

    private static final int THRESHOLD = 16;

    private final Hand hand;

    private Dealer(final Hand hand) {
        this.hand = hand;
    }

    public static Dealer create() {
        return new Dealer(new Hand(new ArrayList<>()));
    }

    @Override
    public void draw(final Card card) {
        hand.add(card);
    }

    @Override
    public boolean canDraw() {
        return hand.calculateOptimalSum() <= THRESHOLD;
    }

    @Override
    public int calculateScore() {
        return hand.calculateOptimalSum();
    }

    public Card findFaceUpCard() {
        return hand.findFirst();
    }

    public Hand getCards() {
        return hand;
    }
}
