package blackjack.domain.participant;

import blackjack.domain.card.CardHand;

import java.util.ArrayList;
import java.util.Objects;

import static blackjack.domain.BlackjackConstant.BLACKJACK_SCORE;

public class Player extends Participant {
    
    private final BettingMoney bettingMoney;
    
    public Player(String name, CardHand cardHand, BettingMoney bettingMoney) {
        super(name, cardHand);
        this.bettingMoney = bettingMoney;
    }
    
    public static Player of(String name, BettingMoney bettingMoney) {
        return new Player(name, new CardHand(new ArrayList<>()), bettingMoney);
    }
    
    @Override
    public boolean canReceive() {
        return sumCardHand() < BLACKJACK_SCORE;
    }
    
    public double getBettingMoney() {
        return bettingMoney.getMoney();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        if (!super.equals(o)) {
            return false;
        }
        
        Player player = (Player) o;
        return Objects.equals(bettingMoney, player.bettingMoney);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bettingMoney);
    }
}
