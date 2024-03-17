package domain.card.deck;

import static java.util.stream.Collectors.toList;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeckFactory {
    private CardDeckFactory() {
    }

    public static CardDeck createCardDeck() {
        List<Card> cards = Arrays.stream(Suit.values())
                .map(CardDeckFactory::createCards)
                .flatMap(List::stream)
                .collect(toList());
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private static List<Card> createCards(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(denomination, suit))
                .collect(toList());
    }
}
