package domain.deck;

import domain.card.Card;
import domain.card.CardShape;
import domain.card.CardValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {

    private final List<Card> cards;

    private CardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck shuffledFullCardDeck() {

        List<Card> cards = Arrays.stream(CardShape.values())
                                 .flatMap(cardShape -> Arrays.stream(CardValue.values())
                                                             .map(cardValue -> new Card(cardShape, cardValue)))
                                 .collect(Collectors.toList());

        Collections.shuffle(cards);

        return new CardDeck(cards);
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public Card draw() {
        return cards.remove(0);
    }
}
