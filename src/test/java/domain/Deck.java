package domain;

import domain.strategy.BlackjackDrawStrategy;
import domain.strategy.DrawStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<TrumpCard> deck;
    private final DrawStrategy drawStrategy;

    public Deck(BlackjackDeckGenerator deckFactory, DrawStrategy drawStrategy){
        this.drawStrategy = drawStrategy;
        this.deck = new ArrayList<>(deckFactory.generateDeck());
    }

    public TrumpCard drawCard() {
        return drawStrategy.draw(deck);
    }
}
