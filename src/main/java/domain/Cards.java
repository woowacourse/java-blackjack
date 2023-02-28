package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card){
        cards.add(card);
    }

    public Card getCard(int index){
        return cards.get(index);
    }

}
