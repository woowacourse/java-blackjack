package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.ArrayList;

public class Player extends Gamer {

    private final Name name;

    private Player(final Name name, final Hand hand) {
        super(hand);
        this.name = name;
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
        return hand.calculateOptimalSum() <= BLACKJACK;
    }

    public Name getName() {
        return name;
    }
}
