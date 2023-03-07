package domain;

import java.util.*;

public class GameResult {
    public final static int BLACK_JACK = 21;
    private final Map<String, List<ResultType>> dealerResult;
    private final Map<String, ResultType> playerResult;

    public GameResult(Dealer dealer, Players players) {
        this.dealerResult = new LinkedHashMap<>();
        this.playerResult = new LinkedHashMap<>();
        calculateResult(dealer, players);
    }

    private void calculateResult(Dealer dealer, Players players) {
        List<ResultType> resultTypes = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            ResultType resultType = isPlayerWin(dealer.getCardsSum(), player.getCardsSum());
            playerResult.put(player.getName(), resultType);
            resultTypes.add(resultType.reverse());
        }
        dealerResult.put(dealer.getName(), resultTypes);
    }

    private ResultType isPlayerWin(int dealerSum, int playerSum) {
        if (playerSum > BLACK_JACK) {
            return ResultType.LOSE;
        }
        if (dealerSum > BLACK_JACK) {
            return ResultType.WIN;
        }
        if (dealerSum > playerSum) {
            return ResultType.LOSE;
        }
        if (dealerSum < playerSum) {
            return ResultType.WIN;
        }
        return ResultType.DRAW;
    }

    public Map<String, List<ResultType>> getDealerResult() {
        return dealerResult;
    }

    public int getDealerResultTypeCount(ResultType resultType) {
        return Collections.frequency(dealerResult.values().stream().findFirst().get(), resultType);
    }
    public Map<String, ResultType> getPlayerResult() {
        return playerResult;
    }

}
