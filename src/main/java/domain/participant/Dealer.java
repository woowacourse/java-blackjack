package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import java.util.ArrayList;

public class Dealer {
    private CardDeck standard;
    private CardDeck hand;

    public Dealer(CardDeck standard) {
        this.standard = standard;
        this.hand = new CardDeck(new ArrayList<>());
    }

    public Card hitCard() {
        return standard.hitCard();
    }
}

