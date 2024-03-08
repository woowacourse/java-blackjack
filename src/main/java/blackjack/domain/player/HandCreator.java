package blackjack.domain.player;

import blackjack.domain.card.CardDeck;

public class HandCreator {

    private static final int INITIAL_HAND_SIZE = 2;

    public Hand createFromDeck(CardDeck cardDeck) {
        return new Hand(cardDeck.popCards(INITIAL_HAND_SIZE));
    }
}
