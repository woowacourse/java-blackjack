package domain.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    
    public static final String NO_CARDS_IN_DECK = "덱에 카드가 없습니다.";
    private static final List<Card> cards = new ArrayList<>();
    
    static {
        for (CardNumber value : CardNumber.values()) {
            for (CardShape cardShape : CardShape.values()) {
                Card card = new Card(value, cardShape);
                cards.add(card);
            }
        }
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(NO_CARDS_IN_DECK);
        }
        return cards.remove(0);
    }
    
    public int getSize() {
        return cards.size();
    }
}
