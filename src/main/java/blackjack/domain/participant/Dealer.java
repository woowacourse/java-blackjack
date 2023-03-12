package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final int CONVERT_TO_DEALER_AMOUNT = -1;

    public Dealer() {
        super(new Name("딜러"));
    }

    public boolean isHit() {
        return this.getState()
                   .isHit();
    }

    @Override
    public void initBetAmount(int value) {
        betAmount.initializeDealer(value * CONVERT_TO_DEALER_AMOUNT);
    }
}
