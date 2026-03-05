package domain;

import constant.Rank;
import constant.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> cards = new ArrayList<>();
    //enum을 순회하면서 조합??

    public void init() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        int lastIndex = cards.size() - 1;
        return cards.remove(lastIndex);
    }

}
