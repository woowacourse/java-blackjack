package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.PickStrategy;

class PlayerTest {

    PickStrategy mockStrategy = cards -> Card.of(Rank.TEN, Suit.CLOVER);
}
