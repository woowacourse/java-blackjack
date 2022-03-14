package blackjack.domain.card.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import java.util.ArrayList;
import java.util.List;

public class FixDeck implements Deck {

    @Override
    public Card pick() {
        return new Card(CardNumber.TEN, CardType.SPADE);
    }

    @Override
    public List<Card> pickTwoCards() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card(CardNumber.TEN, CardType.SPADE));
        newCards.add(new Card(CardNumber.TEN, CardType.HEART));

        return newCards;
    }
}
