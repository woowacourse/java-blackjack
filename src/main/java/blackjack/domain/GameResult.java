package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Hands;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, Result> gameResult;

    private GameResult(Map<Player, Result> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult of(Dealer dealer, Players players) {
        return new GameResult(makeGameResult(dealer, players));
    }

    private static Map<Player, Result> makeGameResult(Dealer dealer, Players players) {
        Map<Player, Result> gameResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            gameResult.put(player, judgeResult(dealer.getHands(), player.getHands()));
        }
        return gameResult;
    }

    private static Result judgeResult(Hands dealerHands, Hands playerHands) {
        if ((dealerHands.isBust() && playerHands.isBust())
                || (playerHands.isSameScore(dealerHands))) {
            return Result.DRAW;
        }
        if (playerHands.isBlackJack()) {
            return Result.BLACK_JACK;
        }
        if (dealerHands.isBust()
                || ((playerHands.isHigherScore(dealerHands)) && !playerHands.isBust())) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    public long getTargetResultCount(Result targetResult) {
        return gameResult.values()
                .stream()
                .filter(result -> result == targetResult)
                .count();
    }

    public long getPlayerResult(Player player) {
        Result playerResult = gameResult.get(player);
        return playerResult.findBetProfit(player.getBetMoney());
    }

    public long getDealerProfit() {
        int totalPlayerProfit = 0;
        for (Player player : gameResult.keySet()) {
            Result playerResult = gameResult.get(player);
            totalPlayerProfit += playerResult.findBetProfit(player.getBetMoney());
        }
        return -totalPlayerProfit;
    }

    public Map<Player, Result> getGameResult() {
        return gameResult;
    }
}
