package blackjack.domain.player;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final int DEALER_LIMIT_SCORE = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean canAddCard() {
        return getTotalScore() <= DEALER_LIMIT_SCORE;
    }
}
