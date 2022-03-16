package blackjack.domain;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.deckstrategy.DeckStrategy;

public class TestDeck implements DeckStrategy {
    @Override
    public Deque<Card> create() {
        // start
        return new ArrayDeque<>(List.of(
            new Card(DIAMOND, JACK), new Card(DIAMOND, TEN),
            new Card(DIAMOND, KING), new Card(DIAMOND, QUEEN),
            new Card(DIAMOND, QUEEN), new Card(CLUB, FIVE)));
    }
}
