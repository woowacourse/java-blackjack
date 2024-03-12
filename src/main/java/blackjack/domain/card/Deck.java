package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private static final List<Card> CACHE = Arrays.stream(CardShape.values())
            .flatMap(cardShape -> Arrays.stream(CardNumber.values())
                    .map(number -> new Card(cardShape, number))).toList();

    private final LinkedList<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>(CACHE);
    }

    public Deck(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.poll();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
