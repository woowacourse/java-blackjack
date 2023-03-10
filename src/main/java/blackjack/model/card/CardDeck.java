package blackjack.model.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class CardDeck {

    private final Stack<Card> cardDeck;

    public CardDeck() {
        cardDeck = new Stack<>();
        List<Card> cards = Arrays.stream(CardSuit.values())
                .flatMap(suit -> Arrays.stream(CardNumber.values())
                        .map(number -> Card.of(suit, number)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        cards.forEach(cardDeck::push);
    }

    public CardDeck(List<Card> cards) {
        cardDeck = new Stack<>();
        cards.forEach(cardDeck::push);
    }

    public Card pick() {
        return cardDeck.pop();
    }
}
