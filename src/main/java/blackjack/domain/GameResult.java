package blackjack.domain;

public class GameResult {
    private final DealerResult dealerResult;
    private final PlayerResult playerResult;

    public GameResult(DealerResult dealerResult, PlayerResult playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public PlayerResult getPlayerResult() {
        return playerResult;
    }
}
