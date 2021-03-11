package blackjack.domain.participant;

import blackjack.domain.card.CardHand;

import java.util.ArrayList;

import static blackjack.domain.BlackjackConstant.BLACKJACK_SCORE;

public class Player extends Participant {
    
    private final double bettingMoney;
    
    public Player(String name, CardHand cardHand, double bettingMoney) {
        super(name, cardHand);
        this.bettingMoney = bettingMoney;
    }
    
    public static Player of(String name, double bettingMoney) {
        return new Player(name.trim(), new CardHand(new ArrayList<>()), bettingMoney);
    }
    
    @Override
    public boolean canReceive() {
        return sumCardHand() < BLACKJACK_SCORE;
    }
}
