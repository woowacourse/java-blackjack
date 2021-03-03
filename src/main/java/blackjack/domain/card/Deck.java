package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final int TOP_OF_DECK = 0;

    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Deck generate() {
        List<Card> cards = Arrays.stream(CardType.values())
                .flatMap(cardType -> Arrays.stream(CardValue.values())
                        .map(cardValue -> new Card(cardType, cardValue)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        return cards.remove(TOP_OF_DECK);
    }
}
