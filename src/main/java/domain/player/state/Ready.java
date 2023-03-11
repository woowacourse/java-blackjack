package domain.player.state;

import domain.card.Card;
import domain.card.Hand;

public class Ready extends Running {
    public Ready() {
        super(new Hand());
    }
    
    @Override
    public State draw(Card card) {
        getHand().addCard(card);
        if (isNotEnoughInitCardsCount()) {
            return this;
        }
        
        if (isBlackJack()) {
            return new BlackJack(getHand());
        }
        return new Hit(getHand());
    }
}
