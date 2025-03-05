package blackjack;

import java.util.ArrayList;

public class Dealer extends Participant {

    private final Deck deck;

    public Dealer(Deck deck) {
        super(new ArrayList<>());
        this.deck = deck;
    }

    public Card drawCard() {
        return deck.draw();
    }
}
