package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

    public static final List<Card> CARDS;

    private final Stack<Card> cards;

    static {
        CARDS = Stream.of(Shape.values())
                .flatMap(shape -> Stream.of(Letter.values())
                        .map(letter -> new Card(shape, letter)))
                .collect(Collectors.toList());
    }

    public Deck() {
        this.cards = new Stack<>();

        Collections.shuffle(CARDS);
        this.cards.addAll(CARDS);
    }

    public Deck(final List<Card> cards) {
        this.cards = new Stack<>();

        this.cards.addAll(cards);
    }

    public Card drawCard() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return cards;
    }
}
