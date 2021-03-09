package blackjack.domain.participant;

import blackjack.domain.card.CardHand;

import java.util.ArrayList;

import static blackjack.domain.BlackjackConstant.BLACKJACK_SCORE;

public class Player extends Participant {
    
    private Player(String name, CardHand cardHand) {
        super(name, cardHand);
    }
    
    public static Player from(String name) {
        return new Player(name, new CardHand(new ArrayList<>()));
    }
    
    @Override
    public boolean canReceive() {
        return sumCardHand() < BLACKJACK_SCORE;
    }
}
