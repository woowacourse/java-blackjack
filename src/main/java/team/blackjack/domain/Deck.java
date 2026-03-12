package team.blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final List<Card> STANDARD_CARDS = createStandardCards();
    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>(STANDARD_CARDS);
        shuffle();
    }

    public Card draw() {
        return cards.removeFirst();
    }

    private static List<Card> createStandardCards() {
        List<Card> defaultCards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                defaultCards.add(new Card(suit, rank));
            }
        }

        return defaultCards;
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }
}
