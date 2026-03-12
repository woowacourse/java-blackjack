package dto;

import domain.GameResult;

public record DealerResultInfo(GameResult gameResult) {

    public int winCount() {
        return gameResult.dealerWinCount();
    }

    public int tieCount() {
        return gameResult.dealerTieCount();
    }

    public int loseCount() {
        return gameResult.dealerLoseCount();
    }
}
