package blackjack.domain.player;

public class Dealer extends Player {

    private static final String NAME = "딜러";

    private static final int MAXIMUM_POINT_TO_PICK = 16;

    @Override
    public Boolean canPick() {
        return holdingCards.sum() <= MAXIMUM_POINT_TO_PICK;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
