package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeckFactory {

    public static Deck create() {
        List<Card> cards = generateCards();
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static List<Card> generateCards() {
        return Stream.of(Denomination.values())
            .flatMap(DeckFactory::generateCardsBySymbol)
            .collect(Collectors.toList());
    }

    private static Stream<Card> generateCardsBySymbol(Denomination denomination) {
        return Stream.of(Suit.values())
            .map(suit -> new Card(denomination, suit));
    }
}

