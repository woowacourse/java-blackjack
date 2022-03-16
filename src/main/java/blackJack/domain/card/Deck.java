package blackJack.domain.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class Deck {

    private final Queue<Card> deck;

    public Deck(List<Card> cards) {
        Objects.requireNonNull(cards, "카드들의 값이 null 입니다.");
        this.deck = new ArrayDeque<>(cards);
    }

    public static Deck create() {
        final List<Card> cards = Card.valuesOf();
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    public Card getCard() {
       return deck.poll();
    }
}
