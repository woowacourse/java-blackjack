package domain.participant;

import domain.card.CardDeck;
import java.util.ArrayList;

public class Participant {
    protected final CardDeck hand;

    public Participant() {
        this.hand = new CardDeck(new ArrayList<>());
    }

    public void hitCards(final CardDeck standard){
        hand.hitCards(standard);
    }

    public int sum(){
        return hand.sumWithAce();
    }

    public CardDeck getHand() {
        return hand;
    }
}
