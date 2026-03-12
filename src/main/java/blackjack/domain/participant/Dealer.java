package blackjack.domain.participant;

public class Dealer extends Participant {
    private static final int MIN_CARD_SUM = 17;

    @Override
    public boolean canReceive() {
        return score.isLess(MIN_CARD_SUM);
    }
}
