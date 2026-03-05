package domain.player;

import dto.DealerCardInfo;

public class Dealer extends Player {
    private static final int DEALER_STOP_SCORE = 17;

    public boolean canStand() {
        int score = adjustBustScore(score());
        return score >= DEALER_STOP_SCORE || score == 0;
    }

    public String showFirstCard() {
        return handCard.getFirstCardInfo();
    }

    public DealerCardInfo getCardInfo() {
        return new DealerCardInfo(handCard.getCardInfos());
    }
}
