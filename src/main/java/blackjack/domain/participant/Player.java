package blackjack.domain.participant;

import blackjack.domain.card.CardHand;

import java.util.ArrayList;

public class Player extends Participant {
    
    private static final int MAXIMUM_THRESHOLD = 21;
    
    private Player(String name, CardHand cardHand) {
        super(name, cardHand);
    }
    
    public static Player from(String name) {
        return new Player(name, new CardHand(new ArrayList<>()));
    }
    
    @Override
    public boolean canReceive() {
        return sumCardHand() < MAXIMUM_THRESHOLD;
    }
}
