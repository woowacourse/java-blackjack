package blackjack.domain;

import blackjack.domian.Card;
import blackjack.domian.Rank;
import blackjack.domian.Suit;
import java.util.ArrayList;
import java.util.List;

public class CardTypes {
    public static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        return deck;
    }
}
