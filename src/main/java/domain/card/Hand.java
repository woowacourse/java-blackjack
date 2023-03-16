package domain.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Hand implements Iterable<Card> {
    
    private final List<Card> cards;
    
    public Hand() {
        this(new ArrayList<>());
    }
    
    private Hand(List<Card> cards) {
        this.cards = cards;
    }
    
    public int calculateScore() {
        Score score = this.cards.stream()
                .map(Card::getScore)
                .reduce(Score.base(), Score::add);
        if (this.hasAce()) {
            score = score.addTenIfInBoundary();
        }
        return score.value();
    }
    
    private boolean hasAce() {
        return this.cards.stream().anyMatch(domain.card.Card::isAce);
    }
    
    public Hand add(Card card) {
        List<Card> newCards = new ArrayList<>(this.cards);
        newCards.add(card);
        return new Hand(newCards);
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
