package blackjack.domain;

public class Dealer extends Participant {

    private static final int CONVERT_TO_DEALER_AMOUNT = -1;

    private final BetAmount betAmount;

    public Dealer() {
        super(new Name("딜러"));
        this.betAmount = new BetAmount();
    }

    public boolean isHit() {
        return this.getState()
                   .isHit();
    }

    @Override
    public void initBetAmount(int value) {
        betAmount.initialize(value * CONVERT_TO_DEALER_AMOUNT);
    }
}
