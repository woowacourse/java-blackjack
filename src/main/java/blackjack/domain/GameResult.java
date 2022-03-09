package blackjack.domain;

public class GameResult {
    private final DealerResult dealerResult;

    public GameResult(DealerResult dealerResult) {
        this.dealerResult = dealerResult;
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }
}
