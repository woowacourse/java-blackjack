package domain.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CardCollection implements Iterable<Card> {
    
    private final List<Card> cards;
    
    public CardCollection() {
        this(new ArrayList<>());
    }
    
    private CardCollection(List<Card> cards) {
        this.cards = cards;
    }
    
    public int calculateScore() {
        int score = 0;
        for (Card card : this.cards) {
            score += card.getScore();
        }
        if (this.hasAce() && score + 10 <= 21) {
            score += 10;
        }
        return score;
    }
    
    private boolean hasAce() {
        return this.cards.stream().anyMatch(domain.card.Card::isAce);
    }
    
    public CardCollection add(Card card) {
        List<Card> newCards = new ArrayList<>(this.cards);
        newCards.add(card);
        return new CardCollection(newCards);
    }
    
    public int size() {
        return this.cards.size();
    }
    
    public Card get(int i) {
        return this.cards.get(i);
    }
    
    @Override
    public Iterator<Card> iterator() {
        return this.cards.iterator();
    }
    
    public Stream<Card> stream() {
        return this.cards.stream();
    }
}
