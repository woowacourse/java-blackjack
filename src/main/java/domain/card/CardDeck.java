package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {
    private final Stack<Card> cards;

    private CardDeck(Stack<Card> cards) {
        Collections.shuffle(cards);
        this.cards = cards;
    }

    public static CardDeck of() {
        List<TrumpNumber> numbers = Arrays.stream(TrumpNumber.values()).toList();
        List<TrumpShape> shapes = Arrays.stream(TrumpShape.values()).toList();
        List<Card> cards = numbers.stream()
                .flatMap(number -> shapes.stream().map(shape -> Card.of(number, shape)))
                .toList();
        Stack<Card> deck = new Stack<>();
        deck.addAll(cards);
        return new CardDeck(deck);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card popCard() {
        return cards.pop();
    }
}
