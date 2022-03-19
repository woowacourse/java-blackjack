package blackjack.domain.card.deckstrategy;

import static camp.nextstep.edu.missionutils.Randoms.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;

public final class ShuffleDeck implements DeckStrategy {

    @Override
    public Deque<Card> create() {
        List<Card> list = getCards();

        return new ArrayDeque<>(shuffle(list));
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
