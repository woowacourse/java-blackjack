package blackjack.dto;

import blackjack.domain.GameResult;

import java.util.HashMap;
import java.util.Map;

public class DealerResult {

    private final Map<GameResult, Integer> dealerResult;

    private DealerResult(final Map<GameResult, Integer> dealerResult) {
        this.dealerResult = dealerResult;
    }

    public static DealerResult of(final int winsCount, final int losesCount, final int drawsCount) {
        Map<GameResult, Integer> dealerResult = new HashMap<>();

        dealerResult.put(GameResult.WIN, winsCount);
        dealerResult.put(GameResult.LOSE, losesCount);
        dealerResult.put(GameResult.DRAW, drawsCount);

        return new DealerResult(dealerResult);
    }

    public static DealerResult createByGameResult(final GameResult gameResult, final DealerResult dealerResult) {
        int winsCount = dealerResult.findResultByGameResult(GameResult.WIN);
        int losesCount = dealerResult.findResultByGameResult(GameResult.LOSE);
        int drawsCount = dealerResult.findResultByGameResult(GameResult.DRAW);

        if (gameResult.equals(GameResult.WIN)) {
            winsCount += 1;
        }

        if (gameResult.equals(GameResult.LOSE)) {
            losesCount += 1;
        }

        if (gameResult.equals(GameResult.DRAW)) {
            drawsCount += 1;
        }

        return DealerResult.of(winsCount, losesCount, drawsCount);
    }

    public int findResultByGameResult(final GameResult gameResult) {
        return dealerResult.get(gameResult);
    }

    public void updateByDealerResult(final DealerResult dealerResult) {
        for (GameResult gameResult : GameResult.values()) {
            this.dealerResult.put(gameResult, dealerResult.findResultByGameResult(gameResult));
        }
    }
}
