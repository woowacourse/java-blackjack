package blackjack.dto;

import blackjack.domain.GameResult;

public class BlackjackResult {

    private final DealerResult dealerResult;
    private final PlayerResult playerResult;

    public BlackjackResult(final DealerResult dealerResult, final PlayerResult playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public GameResult findPlayerResultByName(final String name) {
        return playerResult.findByName(name);
    }

    public int countWins() {
        return dealerResult.getWins();
    }

    public int countLoses() {
        return dealerResult.getLoses();
    }
}
