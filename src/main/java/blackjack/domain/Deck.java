package blackjack.domain;

import blackjack.domain.Card.Shape;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck createOrderedDeck() {
        List<Card> cards = Arrays.stream(Shape.values())
                .map(Deck::makeCards)
                .flatMap(List::stream)
                .toList();

        return new Deck(cards);
    }

    private static List<Card> makeCards(Shape shape) {
        return Arrays.stream(Card.Value.values())
                .map(value -> new Card(value, shape))
                .toList();
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드를 더 이상 뽑을 수 없습니다.");
        }
        return cards.poll();
    }
}
