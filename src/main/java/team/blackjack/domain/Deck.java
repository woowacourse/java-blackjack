package team.blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for(Suit suit : Suit.values()){
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit,rank));
            }
        }

        shuffle();
    }

    public Card draw() {
        shuffle();
        try {
            return cards.getFirst();
        } finally {
            cards.removeFirst();
        }
    }

    private void shuffle(){
        Collections.shuffle(this.cards);
    }
}
