package blackjack.domain.player;

import java.util.List;

public class Dealer extends Player {

    public static final String NAME = "딜러";

    private static final int MAXIMUM_POINT_TO_PICK = 16;

    @Override
    public Boolean canPick() {
        List<Integer> sumPossibility = holdingCards.getSums();
        return sumPossibility.stream()
                .anyMatch(sum -> sum <= MAXIMUM_POINT_TO_PICK);
    }

    @Override
    public Boolean isDealer() {
        return true;
    }

    @Override
    public Boolean isChallenger() {
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
