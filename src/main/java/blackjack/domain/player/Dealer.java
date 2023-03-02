package blackjack.domain.player;

public class Dealer extends Player {

    private static final int MAXIMUM_POINT_TO_PICK = 16;

    @Override
    public Boolean canPick() {
        return holdingCards.sum() <= MAXIMUM_POINT_TO_PICK;
    }
}
