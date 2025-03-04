package domain;

import domain.strategy.DrawStrategy;
import java.util.Deque;
import java.util.LinkedList;

public class Deck {

    private final Deque<TrumpCard> deck;
    private final DrawStrategy drawStrategy;

    public Deck(BlackjackDeckGenerator deckFactory, DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
        this.deck = new LinkedList<>(deckFactory.generateDeck());
    }

    public TrumpCard drawCard() {
        return drawStrategy.draw(deck);
    }
}
