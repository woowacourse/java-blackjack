package domain.player.state;

import domain.card.Hand;

public class Bust extends Finished {
    Bust(Hand hand) {
        super(hand);
    }
    
    @Override
    public double calculateProfit(double betAmount) {
        return betAmount * -1.0;
    }
}
