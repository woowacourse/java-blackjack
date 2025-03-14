package blackjack.factory;

import blackjack.domain.Deck;

public class DeckFactory {

    private final DeckGenerator deckGenerator;

    public DeckFactory(DeckGenerator deckGenerator) {
        this.deckGenerator = deckGenerator;
    }

    public Deck generate() {
        return Deck.shuffled(deckGenerator.generate());
    }
}
