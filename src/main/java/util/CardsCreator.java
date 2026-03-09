package util;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.LinkedList;
import java.util.List;

public class CardsCreator {

    public static List<Card> createLinkedCards() {
        List<Card> cards = new LinkedList<>();
        for (Rank rank : Rank.values()) {
            addCards(rank, cards);
        }

        return cards;
    }

    private static void addCards(Rank rank, List<Card> cards) {
        for (Suit suit : Suit.values()) {
            cards.add(new Card(rank, suit));
        }
    }
}
