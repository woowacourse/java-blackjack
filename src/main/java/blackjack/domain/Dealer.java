package blackjack.domain;

import java.util.List;

public class Dealer extends Player {

    private final Deck deck;

    public Dealer(Deck deck) {
        super("딜러", new Hand());
        this.deck = deck;
    }

    public Card draw() {
        return deck.draw();
    }

    public List<Card> doubleDraw() {
        return List.of(draw(), draw());
    }
}
