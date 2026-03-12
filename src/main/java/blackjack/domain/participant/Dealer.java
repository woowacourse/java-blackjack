package blackjack.domain.participant;

public class Dealer extends Participant {
    private static final int MIN_CARD_SUM = 17;

    @Override
    public boolean canReceive() {
        return isLowerThan(MIN_CARD_SUM);
    }
}
