package domain;

public final class DealerResult {
    private final BettingResult bettingResult;

    public DealerResult(final BettingResult bettingResult) {
        this.bettingResult = bettingResult;
    }

    public BettingResult getBettingResult() {
        return bettingResult;
    }
}
