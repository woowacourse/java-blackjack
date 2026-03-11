package domain.result;

public class MatchResult {

    private final PlayerMatchResult playerMatchResult;
    private final DealerMatchResult dealerMatchResult;

    public MatchResult(PlayerMatchResult playerMatchResult, DealerMatchResult dealerMatchResult) {
        this.playerMatchResult = playerMatchResult;
        this.dealerMatchResult = dealerMatchResult;
    }

    public PlayerMatchResult getPlayerMatchResult() {
        return playerMatchResult;
    }

    public DealerMatchResult getDealerMatchResult() {
        return dealerMatchResult;
    }
}
