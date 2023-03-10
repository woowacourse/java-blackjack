package domain.state;

import domain.card.Card;
import domain.card.Hand;

public class Hit extends State {
    public Hit(Hand hand) {
        super(hand);
    }
    
    @Override
    public State draw(Card card) {
        getHand().addCard(card);
        if (getHand().isBust()) {
            return new Bust(getHand());
        }
        
        return this;
    }
}
