package domain.card.deck;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeckFactory {
    private CardDeckFactory() {
    }

    public static CardDeck createCardDeck() {
        List<Card> cards = Arrays.stream(Suit.values())
                .map(CardDeckFactory::createCards)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private static List<Card> createCards(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(denomination, suit))
                .collect(Collectors.toList());
    }
}
