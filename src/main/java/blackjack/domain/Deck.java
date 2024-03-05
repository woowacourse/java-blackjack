package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        // TODO: 중복되는 카드 검사 로직 추가
        this.cards = cards;
    }

    public static Deck from(List<Card> cards) {
        return new Deck(new LinkedList<>(cards));
    }

    public static Deck createShuffledDeck() {
        List<Card> cards = new ArrayList<>();

        // TODO: 구조개선
        for (Shape shape : Shape.values()) {
            Arrays.stream(Value.values())
                    .forEach(value -> cards.add(new Card(shape, value)));
        }
        return new Deck(cards);
    }

    public Card draw() {
        return cards.remove(0);
    }

}
