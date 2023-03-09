package domain.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    
    public static final String NO_CARDS_IN_DECK = "덱에 카드가 없습니다.";
    private final List<Card> cards;
    
    public Deck() {
        this.cards = this.generateCards();
    }
    
    private List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        for (CardNumber value : CardNumber.values()) {
            this.generateCard(cards, value);
        }
        return cards;
    }
    
    private void generateCard(List<Card> cards, CardNumber value) {
        for (CardShape cardShape : CardShape.values()) {
            Card card = new Card(value, cardShape);
            cards.add(card);
        }
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
