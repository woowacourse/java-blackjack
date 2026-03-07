package factory;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {
    public static List<Card> createDeck() {
        List<Card> cards = new ArrayList<>();
        for(Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        return cards;
    }
}
