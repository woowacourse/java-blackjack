package blackjack.domain.player;

import java.util.List;

public class Dealer extends Player {

    private static final String NAME = "딜러";

    private static final int MAXIMUM_POINT_TO_PICK = 16;

    @Override
    public Boolean canPick() {
        List<Integer> sumPossibility = holdingCards.getSums();
        for (Integer sum : sumPossibility) {
            if (sum <= MAXIMUM_POINT_TO_PICK) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
