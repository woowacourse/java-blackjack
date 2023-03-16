package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int BLACK_JACK = 21;

    private final Map<Player, ResultType> playerResult;

    public GameResult(Dealer dealer, Players players) {
        this.playerResult = new LinkedHashMap<>();
        calculateResultType(dealer, players);
    }

    private void calculateResultType(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            ResultType resultType = calculateResultType(dealer, player);
            playerResult.put(player, resultType);
        }
    }

    private ResultType calculateResultType(Dealer dealer, Player player) {
        if (isBlackJack(dealer, player)) {
            return ResultType.BLACKJACK;
        }
        if (isLose(dealer.getCardsSum(), player.getCardsSum())) {
            return ResultType.LOSE;
        }
        return ResultType.WIN;
    }

    private boolean isBlackJack(Dealer dealer, Player player) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private boolean isLose(int dealerSum, int playerSum) {
        return playerSum > BLACK_JACK || (dealerSum < BLACK_JACK && dealerSum >= playerSum);
    }

    public Map<Player, ResultType> getPlayerResult() {
        return playerResult;
    }

}
