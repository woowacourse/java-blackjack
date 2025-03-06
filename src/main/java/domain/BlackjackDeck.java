package domain;

import domain.strategy.DrawStrategy;
import java.util.Deque;

public class BlackjackDeck {

    private final DrawStrategy drawStrategy;
    private final Deque<TrumpCard> deck;

    public BlackjackDeck(Deque<TrumpCard> deck, DrawStrategy drawStrategy) {
        this.deck = deck;
        this.drawStrategy = drawStrategy;
    }

    public TrumpCard drawCard() {
        return drawStrategy.draw(deck);
    }
}
