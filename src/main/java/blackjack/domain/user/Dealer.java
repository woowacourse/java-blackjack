package blackjack.domain.user;

import java.util.List;

public class Dealer extends User {

    private static final String NAME = "딜러";
    private static final int BASES_SCORE_CAN_DRAW = 16;
    private static final int FIRST_INDEX = 0;

    public Dealer() {
        super(NAME);
    }

    public String getFirstCardInfo() {
        List<String> cardsInfos = userCards.getInfos();
        return cardsInfos.get(FIRST_INDEX);
    }

    @Override
    public boolean canDrawCard() {
        return getScore().isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
    }
}
