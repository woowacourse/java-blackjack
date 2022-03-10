package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;
    
    private Cards(){
        this.cards = new ArrayList<>();
    }
    
    public static Cards of() {
        return new Cards();
    }


    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int getPoint() {
        int point = 0;
        for (Card card : cards) {
            point += card.getDenomination().getPoint();
        }
        return point;
    }

    @Override
    public String toString() {
        return cards.toString().substring(1,cards.toString().length()-1);
    }
}
