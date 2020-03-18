package blackjack.domain.gambler;

import java.util.List;

public final class Dealer extends User {

    private static final String NAME = "딜러";
    private static final int BASES_SCORE_CAN_DRAW = 16;
    private static final int FIRST_FROM_INDEX = 0;
    private static final int FIRST_TO_INDEX = 1;

    public Dealer() {
        super(NAME);
    }

    @Override
    public List<String> getFirstCardInfo() {
        return userCards.getInfos().subList(FIRST_FROM_INDEX, FIRST_TO_INDEX);
    }

    @Override
    public boolean canDrawCard() {
        return getScore().isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
    }
}
