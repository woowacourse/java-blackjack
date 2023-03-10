package blackjack.view.dto;

import blackjack.domain.participant.Dealer;

public class DealerStateResponse {

    private final boolean drawable;
    private final int thresholdScore;

    private DealerStateResponse(final boolean drawable, final int thresholdScore) {
        this.drawable = drawable;
        this.thresholdScore = thresholdScore;
    }

    public static DealerStateResponse from(final Dealer dealer) {
        return new DealerStateResponse(dealer.isDrawable(), dealer.getThresholdScore());
    }

    public boolean isDrawable() {
        return drawable;
    }

    public int getThresholdScore() {
        return thresholdScore;
    }
}
