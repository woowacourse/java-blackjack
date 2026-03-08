package blackjack.model;

public class Dealer extends Participant {
    private static final int MIN_CARD_SUM = 16;

    @Override
    public boolean canReceive(int score) {
        return score <= MIN_CARD_SUM;
    }
}
