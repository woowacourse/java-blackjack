package blackJack.domain.card;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class Deck {

    private final Queue<Card> deck;

    public Deck(List<Card> cards) {
        Objects.requireNonNull(cards, "카드들의 값이 null 입니다.");
        this.deck = new ArrayDeque<>(cards);
    }

    public static Deck create() {
        final List<Card> cards = createDeck();
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static List<Card> createDeck() {
        return Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                        .map(denomination -> Card.valueOf(suit, denomination)))
                .collect(Collectors.toList());
    }

    public Card getCard() {
       return deck.poll();
    }
}
