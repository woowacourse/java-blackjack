package blackjack.domain.card.group;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public final class Cards {
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
    
    public Card getFirstCard() {
        return cards.get(0);
    }
    
    public int getRawPoint() {
        return cards.stream()
                .mapToInt(card -> card.getDenomination().getPoint())
                .sum();
    }
    
    public int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }
    
    @Override
    public String toString() {
        return cards.toString().substring(1, cards.toString().length() - 1);
    }
}
