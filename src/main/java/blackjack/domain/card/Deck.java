package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private static final int TOP_OF_DECK = 0;
    private static final int NUMBER_OF_INIT_CARD = 2;
    private static final List<Card> CARDS;

    private final List<Card> cards;

    static {
        CARDS = Arrays.stream(CardType.values())
                .flatMap(cardType -> Arrays.stream(CardValue.values())
                        .map(cardValue -> new Card(cardType, cardValue)))
                .collect(Collectors.toList());
    }

    private Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Deck generate() {
        List<Card> cards = new ArrayList<>(CARDS);
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        return cards.remove(TOP_OF_DECK);
    }

    public List<Card> handOutInitCards() {
        return Stream.generate(this::draw)
                .limit(NUMBER_OF_INIT_CARD)
                .collect(Collectors.toList());
    }
}
