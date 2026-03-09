package domain.player;

import dto.DealerCardInfo;

public class Dealer extends Player {
    private static final int DEALER_STOP_SCORE = 17;

    public boolean canStand() {
        int score = score();
        if (isBust()) {
            return true;
        }
        return score >= DEALER_STOP_SCORE;
    }

    public String getFirstCardInfo() {
        return handCard.getFirstCardInfo();
    }

    public DealerCardInfo getCardInfo() {
        return new DealerCardInfo(handCard.getCardInfos());
    }
}
