package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.ArrayList;

public class Dealer implements Gamer {

    private static final int THRESHOLD = 16;

    private final Deck deck;
    private final Hand hand;

    private Dealer(final Deck deck, final Hand hand) {
        this.deck = deck;
        this.hand = hand;
    }

    public static Dealer from(final Deck deck) {
        return new Dealer(deck, new Hand(new ArrayList<>()));
    }

    public void drawInitialHand() {
        draw();
        draw();
    }

    public void draw() {
        hand.add(deck.pop());
    }

    public Card drawPlayerCard() {
        return deck.pop();
    }

    public Card openFirstCard() {
        return hand.findFirst();
    }

    @Override
    public boolean canDraw() {
        return hand.calculateOptimalSum() <= THRESHOLD;
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

    public Hand getHand() {
        return hand;
    }
}
