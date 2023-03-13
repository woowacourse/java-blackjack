package domain.player.state;

import domain.card.Card;
import domain.card.Hand;

public class Ready extends Running {
    public Ready() {
        this(new Hand());
    }
    
    private Ready(Hand hand) {
        super(hand);
    }
    
    @Override
    public State draw(Card card) {
        getHand().addCard(card);
        if (isNotEnoughInitCardsCount()) {
            return new Ready(getHand());
        }
        
        if (isBlackJack()) {
            return new BlackJack(getHand());
        }
        return new Hit(getHand());
    }
}
