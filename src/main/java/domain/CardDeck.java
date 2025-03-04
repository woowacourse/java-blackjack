package domain;

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
        Stack<Card> cards = new Stack<>();
        for (TrumpNumber number : numbers) {
            for (TrumpShape shape : shapes) {
                cards.add(Card.of(number, shape));
            }
        }
        return new CardDeck(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card popCard() {
        return cards.pop();
    }
}
