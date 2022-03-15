package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Human {
    protected final Name name;
    protected final Cards cards;
    
    protected Human(Cards cards, String name) {
        this.name = Name.valueOf(name);
        this.cards = cards;
    }
    
    public abstract boolean isAbleToHit();
    
    public void addCard(final Card card) {
        cards.add(card);
    }
    
    public int getPoint() {
        return cards.getPoint();
    }
    
    public String getName() {
        return name.get();
    }
    
    public Cards getCards() {
        return cards;
    }
}
