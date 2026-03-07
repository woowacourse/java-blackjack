package util;

import domain.Card;
import domain.Rank;
import domain.Suit;
import java.util.LinkedList;
import java.util.List;

public class CardsCreator {

    public static List<Card> createLinkedCards() {
        List<Card> cards = new LinkedList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }

        return cards;
    }
}
