package domain.card;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class CardFactory {
    private CardFactory() {
    }

    public static CardDeck createCardDeck() {
        return Arrays.stream(Suit.values())
                .map(CardFactory::createCards)
                .flatMap(List::stream)
                .collect(collectingAndThen(toList(), CardDeck::new));
    }

    private static List<Card> createCards(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(denomination, suit))
                .collect(toList());
    }
}
