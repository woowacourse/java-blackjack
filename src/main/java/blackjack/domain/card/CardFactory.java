package blackjack.domain.card;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardFactory {

    static List<Card> generateCards() {
        return Stream.of(Denomination.values())
            .flatMap(CardFactory::generateCardsBySymbol)
            .collect(Collectors.toList());
    }

    static Stream<Card> generateCardsBySymbol(Denomination denomination) {
        return Stream.of(Suit.values())
            .map(suit -> new Card(denomination, suit));
    }
}

