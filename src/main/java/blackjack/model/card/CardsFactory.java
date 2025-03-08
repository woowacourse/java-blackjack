package blackjack.model.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CardsFactory {

    private CardsFactory() {
    }

    public static List<Card> createStandardDeckCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            cards.addAll(createCardsForSuit(suit));
        }
        return cards;
    }

    private static List<Card> createCardsForSuit(Suit suit) {
        return Arrays.stream(CardValue.values())
                .map(cardValue -> new Card(suit, cardValue))
                .collect(Collectors.toList());
    }
}
