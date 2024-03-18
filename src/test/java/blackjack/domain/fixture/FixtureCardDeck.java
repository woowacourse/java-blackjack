package blackjack.domain.fixture;

import blackjack.domain.card.CardDeck;

public class FixtureCardDeck {

    public static final CardDeck NOT_SHUFFLED_CARD_DECK = CardDeck.of(cards -> cards);
}