package blackjack.domain.participant;

import blackjack.domain.card.CardHand;
import blackjack.domain.card.CompeteResult;

import java.util.ArrayList;

public class Player extends Participant {
    
    private static final int MAXIMUM_THRESHOLD = 21;
    
    private Player(String name, CardHand cardHand) {
        super(name, cardHand);
    }
    
    public static Player from(String name) {
        return new Player(name, new CardHand(new ArrayList<>()));
    }
    
    public CompeteResult compete(Dealer dealer) {
        if (isPlayerWin(dealer)) {
            return CompeteResult.WIN;
        }
        
        if (isPlayerDefeat(dealer)) {
            return CompeteResult.DEFEAT;
        }
        
        return CompeteResult.DRAW;
    }
    
    private boolean isPlayerWin(Dealer dealer) {
        return sumCardHand() > dealer.sumCardHand();
    }
    
    private boolean isPlayerDefeat(Dealer dealer) {
        return sumCardHand() < dealer.sumCardHand();
    }
}
