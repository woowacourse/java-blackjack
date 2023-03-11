package domain.player.state;

import domain.card.Hand;

public class Stay extends Finished {
    public Stay(Hand hand) {
        super(hand);
    }
    
    @Override
    public double calculateProfit(double betAmount) {
        return betAmount;
    }
}
