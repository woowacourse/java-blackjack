package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        this.cards = generateDeck();
    }

    private List<Card> generateDeck() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Suit.values())
            .forEach((suit) -> cards.addAll(generateCardsBySuit(suit)));
        return cards;
    }

    private List<Card> generateCardsBySuit(Suit suit) {
        return Arrays.stream(Denomination.values())
            .map((denomination -> new Card(denomination, suit)))
            .collect(Collectors.toList());
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.remove(0);
    }

    public int getCurrentSize() {
        return this.cards.size();
    }
}
