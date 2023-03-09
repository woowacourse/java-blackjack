package domain;

import java.util.*;

public class GameResult {
    private static final int BLACK_JACK = 21;
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
        if (isWin(dealerSum, playerSum)) {
            return ResultType.WIN;
        }
        if (isLose(dealerSum, playerSum)) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }

    private boolean isWin(int dealerSum, int playerSum) {
        return dealerSum > BLACK_JACK || dealerSum < playerSum;
    }

    private boolean isLose(int dealerSum, int playerSum) {
        return playerSum > BLACK_JACK || dealerSum > playerSum && dealerSum <= BLACK_JACK;
    }

    public Map<String, List<ResultType>> getDealerResult() {
        return dealerResult;
    }

    public Map<String, ResultType> getPlayerResult() {
        return playerResult;
    }

}
