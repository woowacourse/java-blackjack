package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Hands;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int INITIAL_DRAW_CARD_NUMBER = 2;
    private static final int BLACK_JACK = 21;

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
                || (dealerHands.getHandsScore() == playerHands.getHandsScore())) {
            return Result.DRAW;
        }
        if (playerHands.size() == INITIAL_DRAW_CARD_NUMBER && playerHands.getHandsScore() == BLACK_JACK) {
            return Result.BLACK_JACK;
        }
        if (dealerHands.isBust()
                || ((playerHands.getHandsScore() > dealerHands.getHandsScore()) && !playerHands.isBust())) {
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
        return gameResult.get(player).getBetProfit(player.getBetMoney());
    }

    public long getDealerProfit() {
        int totalPlayerProfit = 0;
        for (Player player : gameResult.keySet()) {
            totalPlayerProfit += gameResult.get(player).getBetProfit(player.getBetMoney());
        }
        return -totalPlayerProfit;
    }

    public Map<Player, Result> getGameResult() {
        return gameResult;
    }
}
