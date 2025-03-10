package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardDeck {

    private final Deque<Card> cards;

    private CardDeck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck of() {
        List<Card> cards = generateCardDeck();
        Collections.shuffle(cards);
        Deque<Card> deck = new ArrayDeque<>(cards);
        return new CardDeck(deck);
    }

    public List<Card> getCards() {
        return cards.stream().toList();
    }

    public Card popCard() {
        return cards.pollLast();
    }

    private static List<Card> generateCardDeck() {
        List<TrumpNumber> numbers = Arrays.stream(TrumpNumber.values()).toList();
        List<TrumpShape> shapes = Arrays.stream(TrumpShape.values()).toList();
        return new ArrayList<>(numbers.stream()
                .flatMap(number -> shapes.stream().map(shape -> Card.of(number, shape)))
                .toList());
    }
}
