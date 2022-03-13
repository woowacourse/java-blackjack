package blackjack.domain.card.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.deck.Deck;

public class FixDeck implements Deck {
    @Override
    public Card pick() {
        return new Card(CardNumber.TEN, CardType.SPADE);
    }
}
