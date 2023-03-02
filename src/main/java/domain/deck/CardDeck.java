package domain.deck;

import domain.card.Card;
import domain.card.CardShape;
import domain.card.CardValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

public class CardDeck {

    private final Stack<Card> cards;

    private CardDeck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck shuffledFullCardDeck() {

        Stack<Card> cards = Arrays.stream(CardShape.values())
                                  .flatMap(cardShape -> Arrays.stream(CardValue.values())
                                                              .map(cardValue -> new Card(cardShape, cardValue)))
                                  .collect(Collectors.toCollection(Stack::new));

        Collections.shuffle(cards);

        return new CardDeck(cards);
    }

    public Card draw() {
        return cards.pop();
    }
}
