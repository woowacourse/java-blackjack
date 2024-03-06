package blackjack.dto;

public class GameResult {

    private final DealerResult dealerResult;
    private final PlayerResult playerResult;

    private GameResult(final DealerResult dealerResult, final PlayerResult playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public static GameResult from(final int wins, final int loses, final PlayerResult playerResult) {
        DealerResult dealerResult = new DealerResult(wins, loses);

        return new GameResult(dealerResult, playerResult);
    }

    public boolean findPlayerResultByName(final String name) {
        return playerResult.findByName(name);
    }

    public int countWins() {
        return dealerResult.getWins();
    }

    public int countLoses() {
        return dealerResult.getLoses();
    }
}
