package domain.strategy;

import domain.blackjackgame.BlackjackDeck;

public class DeckGenerator {

    public BlackjackDeck generateDeck(DrawStrategy drawStrategy, DeckGenerateStrategy deckGenerateStrategy) {
        return deckGenerateStrategy.generateDeck(drawStrategy);
    }
}
