package blackjack.domain;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;

import java.util.List;
import java.util.Stack;

import blackjack.domain.card.Card;
import blackjack.domain.card.deckstrategy.DeckStrategy;

public class TestDeck implements DeckStrategy {
    @Override
    public Stack<Card> create() {
        Stack<Card> cards = new Stack<>();
        cards.addAll(List.of(
            new Card(DIAMOND, JACK), new Card(DIAMOND, TEN),
            new Card(DIAMOND, KING), new Card(DIAMOND, QUEEN),
            new Card(DIAMOND, QUEEN), new Card(CLUB, FIVE))); // start
        return cards;
    }
}
