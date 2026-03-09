package util;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.List;

public class CardsCreator {

    public static List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }

        return cards;
    }
}
