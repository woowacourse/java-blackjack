package domain.player.state;

import domain.card.Hand;

public class BlackJack extends Finished {
    BlackJack(Hand hand) {
        super(hand);
    }
    
    @Override
    public double calculateProfit(double betAmount) {
        return betAmount * 1.5;
    }
}
