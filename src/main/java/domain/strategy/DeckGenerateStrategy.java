package domain.strategy;

import domain.blackjackgame.BlackjackDeck;

public interface DeckGenerateStrategy {

    BlackjackDeck generateDeck(DrawStrategy drawStrategy);
}
