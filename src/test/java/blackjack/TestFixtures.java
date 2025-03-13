package blackjack;

import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import blackjack.model.card.FixedCardShuffler;
import blackjack.model.participant.Dealer;

public class TestFixtures {
    public static final Deck UNSHUFFLED_DECK = Deck.createShuffledDeck(Card.createDeck(), new FixedCardShuffler());
    public static final Dealer DEALER = new Dealer(UNSHUFFLED_DECK);
}
