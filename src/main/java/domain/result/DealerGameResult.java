package domain.result;

import dto.PlayedGameResult;

public class DealerGameResult {

    private PlayedGameResult dealerResult;

    public void record(PlayedGameResult playedGameResult) {
        this.dealerResult = playedGameResult;
    }

    public PlayedGameResult dealerResult() {
        requireDealerGameResultExists();
        return dealerResult;
    }

    private void requireDealerGameResultExists() {
        if (dealerResult == null) {
            throw new IllegalStateException("딜러 기록이 저장되지 않았습니다.");
        }
    }
}
