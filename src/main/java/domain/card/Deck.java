package domain.card;

import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deck;

    public Deck(List<Card> deck) {
        this.deck = deck;
    }

    public void shuffle(){
        Collections.shuffle(this.deck);
    }

    public Card deal(){
        return this.deck.remove(0);
    }

}
