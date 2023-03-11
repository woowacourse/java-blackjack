package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    
    public static final String NO_CARDS_IN_DECK = "덱에 카드가 없습니다.";
    
    private final List<Card> cards;
    
    public Deck() {
        this.cards = this.generateDeck();
    }
    
    private List<Card> generateDeck() {
        List<Card> cards = new ArrayList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            for (CardShape cardShape : CardShape.values()) {
                cards.add(new Card(cardNumber, cardShape));
            }
        }
        return cards;
    }
    
    public void shuffle() {
        Collections.shuffle(this.cards);
    }
    
    public Card draw() {
        if (this.cards.isEmpty()) {
            throw new IllegalStateException(NO_CARDS_IN_DECK);
        }
        return this.cards.remove(0);
    }
    
    public int getSize() {
        return this.cards.size();
    }
}
