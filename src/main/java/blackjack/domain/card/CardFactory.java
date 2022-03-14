package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardFactory {

    private CardFactory() {
    }

    public static List<Card> createBlackJackCards() {
        List<Card> cards = Arrays.stream(Pattern.values())
            .map(CardFactory::createCardsBy)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        Collections.shuffle(cards);
        return cards;
    }

    private static List<Card> createCardsBy(Pattern pattern) {
        return Arrays.stream(Denomination.values())
            .map(denomination -> new Card(pattern, denomination))
            .collect(Collectors.toList());
    }
}
