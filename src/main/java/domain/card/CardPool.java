package domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CardPool {
    private static final List<Card> POOL = Arrays.stream(CardType.values())
            .flatMap(CardPool::makeCardsWith)
            .toList();


    private static Stream<Card> makeCardsWith(CardType cardType) {
        return Arrays.stream(CardName.values()).map(cardName -> new Card(cardName, cardType));
    }

    static List<Card> allCards() {
        return POOL;
    }

    public static Card get(CardType cardType, CardName cardName) {
        return POOL.stream()
                .filter(card -> card.cardName() == cardName)
                .filter(card -> card.cardType() == cardType)
                .findFirst()
                .orElseThrow();
    }
}
