package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.game.Hand;

import java.util.ArrayList;
import java.util.List;

public abstract class HandFixture {
    public static Hand create(final Card one, final Card two, final List<Card> cards) {
        final List<Card> newCards = new ArrayList<>();
        newCards.add(one);
        newCards.add(two);

        final Deck deck = MockDeck.create(newCards);
        final Hand hand = new Hand(deck);
        cards.forEach(hand::receive);

        return hand;
    }
}
