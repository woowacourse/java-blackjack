package blackjack.domain.card.deckstrategy;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;

public class RandomDeck implements DeckStrategy {
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

    @Override
    public Stack<Card> create() {
        List<Card> list = getCards();
        Collections.shuffle(list);

        Stack<Card> deck = new Stack<>();
        deck.addAll(list);

        return deck;
    }
}
