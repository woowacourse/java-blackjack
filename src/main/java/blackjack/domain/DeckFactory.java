package blackjack.domain;

import blackjack.domain.game.Deck;

public interface DeckFactory {

    Deck generate();
}
