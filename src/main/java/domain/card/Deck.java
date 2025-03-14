package domain.card;

import domain.card.strategy.DrawStrategy;
import java.util.Deque;

public class Deck {

    private final DrawStrategy drawStrategy;
    private final Deque<TrumpCard> deck;

    public Deck(Deque<TrumpCard> deck, DrawStrategy drawStrategy) {
        this.deck = deck;
        this.drawStrategy = drawStrategy;
    }

    public TrumpCard drawCard() {
        return drawStrategy.draw(deck);
    }
}
