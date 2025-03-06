package domain;

import domain.constant.TrumpEmblem;
import domain.constant.TrumpNumber;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final Cards deck;

    private Deck(Cards cards) {
        this.deck = cards;
    }

    public static Deck initialize() {
        List<Card> cards = Arrays.stream(TrumpNumber.values())
                .flatMap(number -> Arrays.stream(TrumpEmblem.values())
                        .map(emblem -> new Card(number, emblem)))
                .collect(Collectors.toList());
        shuffle(cards);
        return new Deck(new Cards(cards));
    }

    private static void shuffle(List<Card> deck) {
        Collections.shuffle(deck);
    }

    public Cards drawInitialCards() {
        return new Cards(Arrays.asList(deck.drawOneCard(), deck.drawOneCard()));
    }

    public Card drawOneCard() {
        return deck.drawOneCard();
    }

    public int getSize() {
        return deck.getSize();
    }
}
