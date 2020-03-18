package blackjack.domain.gambler;

import java.util.List;

public final class Player extends User {

    private static final int BASES_SCORE_CAN_DRAW = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public List<String> getFirstCardInfo() {
        return userCards.getInfos();
    }

    @Override
    public boolean canDrawCard() {
        return getScore().isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
    }
}
