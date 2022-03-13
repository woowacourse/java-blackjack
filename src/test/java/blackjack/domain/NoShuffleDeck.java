package blackjack.domain;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.deckstrategy.DeckStrategy;

public class NoShuffleDeck implements DeckStrategy {
    @Override
    public Stack<Card> create() {
        List<Card> list = getCards();

        Stack<Card> deck = new Stack<>();
        deck.addAll(list);

        return deck;
    }

    private static List<Card> getCards() {
        return Arrays.stream(CardSymbol.values())
            .flatMap(symbol -> createSymbolCards(symbol).stream())
            .collect(toList());
    }

    private static List<Card> createSymbolCards(CardSymbol symbol) {
        return Arrays.stream(CardNumber.values())
            .map(cardNumber -> new Card(symbol, cardNumber))
            .collect(toUnmodifiableList());
    }
}
