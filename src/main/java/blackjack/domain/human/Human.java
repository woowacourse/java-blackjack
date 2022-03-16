package blackjack.domain.human;

import static blackjack.util.Constants.BLACKJACK_NUMBER;

import blackjack.domain.card.Card;
import blackjack.domain.card.group.Cards;
import blackjack.domain.human.element.Name;
import blackjack.domain.result.Point;

public abstract class Human {
    protected final Name name;
    protected final Cards cards;
    
    protected Human(Cards cards, String name) {
        this.name = Name.valueOf(name);
        this.cards = cards;
    }
    
    public void addCard(final Card card) {
        cards.add(card);
    }
    
    public Card getInitCard() {
        return cards.getFirstCard();
    }
    
    public int getPoint() {
        return Point.fromCards(cards).get();
    }
    
    public boolean isBust() {
        return getPoint() > BLACKJACK_NUMBER;
    }
    
    public String getName() {
        return name.get();
    }
    
    public Cards getCards() {
        return new Cards(cards);
    }
    
    @Override
    public String toString() {
        return "Human{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
