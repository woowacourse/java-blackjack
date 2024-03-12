package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.ArrayList;

public class Player implements Gamer {

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
        return hand.calculateOptimalSum() <= BLACKJACK;
    }

    @Override
    public boolean isBust() {
        return calculateScore() > BLACKJACK;
    }

    @Override
    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK && hand.hasOnlyInitialCard();
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
