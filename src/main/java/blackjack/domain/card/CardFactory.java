package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CardFactory {

    private CardFactory() {
    }

    public static List<Card> create() {
        return Arrays.stream(Suit.values())
                .map(CardFactory::generateCards)
                .flatMap(Collection::stream)
                .toList();
    }

    private static List<Card> generateCards(final Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(suit, denomination))
                .toList();
    }
}
