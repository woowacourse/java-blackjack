package domain.player.state;

import domain.card.Card;
import domain.card.Hand;

public class Hit extends Running {
    public Hit(Hand hand) {
        super(hand);
    }
    
    @Override
    public State draw(Card card) {
        hand.addCard(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        
        return new Hit(hand);
    }
}
