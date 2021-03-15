package blackjack.domain.participant;

import blackjack.domain.card.CardHand;

import java.util.ArrayList;

import static blackjack.domain.BlackjackConstant.BLACKJACK_SCORE;

public class Player extends Participant {
    
    private final BettingMoney bettingMoney;
    
    public Player(String name, CardHand cardHand, BettingMoney bettingMoney) {
        super(name, cardHand);
        this.bettingMoney = bettingMoney;
    }
    
    public static Player of(String name, BettingMoney bettingMoney) {
        return new Player(name.trim(), new CardHand(new ArrayList<>()), bettingMoney);
    }
    
    @Override
    public boolean canReceive() {
        return sumCardHand() < BLACKJACK_SCORE;
    }
    
    public double calculateProfit(Dealer dealer) {
        return bettingMoney.calculateProfitOfPlayer(dealer, this);
    }
}
