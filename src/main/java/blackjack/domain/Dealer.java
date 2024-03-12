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

    public static Dealer of(final Deck deck) {
        return new Dealer(deck, new Hand(new ArrayList<>()));
    }

    public Card draw() {
        final Card card = deck.pop();
        hand.add(card);
        return card;
    }

    public Card drawPlayerCard() {
        return deck.pop();
    }

    @Override
    public boolean canDraw() {
        return hand.calculateOptimalSum() <= THRESHOLD;
    }

    @Override
    public int calculateScore() {
        return hand.calculateOptimalSum();
    }

    public Hand getHand() {
        return hand;
    }
}
