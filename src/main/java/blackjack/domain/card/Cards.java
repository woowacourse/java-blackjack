package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;
    
    public Cards() {
        this.cards = new ArrayList<>();
    }
    
    public void add(final Card card) {
        cards.add(card);
    }
    
    public int size() {
        return cards.size();
    }
    
    public int getPoint() {
        return Point.of(cards).get();
    }
    
    @Override
    public String toString() {
        return cards.toString().substring(1, cards.toString().length() - 1);
    }
}
