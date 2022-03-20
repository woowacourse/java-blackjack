package blackjack.domain.card.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import java.util.ArrayList;
import java.util.List;

public class OnlyTenSpadePickDeck implements Deck {

    @Override
    public Card pick() {
        return Card.of(CardNumber.TEN, CardType.SPADE);
    }

    @Override
    public List<Card> pickTwoCards() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(Card.of(CardNumber.TEN, CardType.SPADE));
        newCards.add(Card.of(CardNumber.TEN, CardType.HEART));

        return newCards;
    }
}
