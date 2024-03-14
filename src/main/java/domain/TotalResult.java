package domain;

import domain.gamer.Player;

import java.util.Map;

public class TotalResult {
    private final DealerResult dealerResult;
    private final PlayerResults playerResult;

    public TotalResult(final DealerResult dealerResult, final PlayerResults playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public int getDealerWinCount() {
        return dealerResult.getWinCount();
    }

    public int getDealerLoseCount() {
        return dealerResult.getLoseCount();
    }

    public int getDealerTieCount() {
        return dealerResult.getTieCount();
    }

    public Map<Player, Result> getPlayerResult() {
        return playerResult.getResults();
    }
}
