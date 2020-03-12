package blackjack.card.domain;

import blackjack.card.domain.component.CardNumber;
import blackjack.card.domain.component.Symbol;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardFactory implements CardCreateStrategy {

    public List<Card> getCards() {
        return Arrays.stream(Symbol.values())
                .flatMap(this::makeCard)
                .collect(Collectors.toList());
    }

    private Stream<Card> makeCard(Symbol symbol) {
        return Arrays.stream(CardNumber.values())
                .map(number -> Card.of(symbol, number));
    }
}
