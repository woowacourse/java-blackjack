package domain;

import domain.constant.TrumpEmblem;
import domain.constant.TrumpNumber;
import java.util.ArrayList;
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

    public Cards drawInitialCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(deck.drawOneCard());
        cards.add(deck.drawOneCard());
        return new Cards(cards);
    }

    public Card drawOneCard() {
        return deck.drawOneCard();
    }

    private static void shuffle(List<Card> deck) {
        Collections.shuffle(deck);
    }

    public int getSize() {
        return deck.getSize();
    }

}
