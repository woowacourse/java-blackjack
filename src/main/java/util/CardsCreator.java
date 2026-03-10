package util;

import domain.card.vo.Card;
import domain.card.vo.Rank;
import domain.card.vo.Suit;
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
