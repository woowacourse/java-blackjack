package blackjack.domain;

import java.util.*;

public class Deck {
    private final Queue<Card> cards = new LinkedList<>();

    public Deck() {
        generateDeck();
    }

    private void generateDeck() {
        for (Suit suit : Suit.values()) {
            generateCardEachSuit(suit);
        }
        Collections.shuffle((List<?>) cards);
    }

    private void generateCardEachSuit(Suit suit) {
        for (Letter letter : Letter.values()) {
            cards.add(new Card(suit, letter));
        }
    }

    public Card getCard() {
        return cards.poll();
    }
}
