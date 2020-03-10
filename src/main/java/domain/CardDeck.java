package domain;

import java.util.Collections;
import java.util.List;

public class CardDeck {
    List<Card> cardDeck;

    public CardDeck(List<Card> cardDeck){
        this.cardDeck = cardDeck;
    }

    public void shuffle(){
        Collections.shuffle(cardDeck);
    }

    public Card drawOne(){
        return cardDeck.remove(0);
    }
}
