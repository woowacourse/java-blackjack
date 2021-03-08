package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {

    public static final int TOTAL_CARD_COUNT = 52;

    private final List<Card> deck;
    private int cardUsed = 0;

    public Deck() {
        deck = new ArrayList<>();
        IntStream.rangeClosed(0, TOTAL_CARD_COUNT - 1)
                .forEach(index -> deck.add(Card.of(index)));
    }

    public Card draw() {
        if (cardUsed == TOTAL_CARD_COUNT) {
            shuffle();
            cardUsed = 0;
        }
        return deck.get(cardUsed++);
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }
}