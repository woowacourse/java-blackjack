package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    public Deck() {
        this.deck =createDeck();
    }

    private List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for(CardPattern cardPattern : CardPattern.values()){
            for (CardPoint cardPoint : CardPoint.values()) {
                deck.add(new Card(cardPoint, cardPattern));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }


    public int getCount(){
        return deck.size();
    }

    public Card draw(){
        return deck.removeLast();
    }


}
