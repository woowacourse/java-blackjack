package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.ArrayList;

public class Player implements Gamer {

    private static final int THRESHOLD = 21;

    private final Name name;
    private final Hand hand;

    private Player(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Player from(final String name) {
        final Hand hand = new Hand(new ArrayList<>());
        return new Player(new Name(name), hand);
    }

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

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
