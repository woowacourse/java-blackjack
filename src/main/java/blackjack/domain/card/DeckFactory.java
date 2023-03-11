package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckFactory {

    public static List<Card> createShuffledCard() {
        List<Card> deck = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            addCards(deck, suit);
        }
        Collections.shuffle(deck);

        return deck;
    }

    private static void addCards(List<Card> createdCard, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            createdCard.add(new Card(suit, denomination));
        }
    }
}
