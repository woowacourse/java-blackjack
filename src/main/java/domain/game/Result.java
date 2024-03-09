package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private final Map<Player, WinLose> resultMap;

    private Result(Map<Player, WinLose> resultMap) {
        this.resultMap = resultMap;
    }

    public static Result of(List<Player> players, Dealer dealer) {
        Map<Player, WinLose> currentResultMap = new LinkedHashMap<>();

        for (Player player : players) {
            WinLose winLose = decideWinLose(player, dealer);
            currentResultMap.put(player, winLose);
        }

        return new Result(currentResultMap);
    }

    private static WinLose decideWinLose(Player player, Dealer dealer) {
        if (player.isBusted()) {
            return WinLose.LOSE;
        }
        if (dealer.isBusted()) {
            return WinLose.WIN;
        }
        return decideWinLoseByScore(player, dealer);
    }

    private static WinLose decideWinLoseByScore(Player player, Dealer dealer) {
        if (dealer.calculateScore() < player.calculateScore()) {
            return WinLose.WIN;
        }
        if (dealer.calculateScore() > player.calculateScore()) {
            return WinLose.LOSE;
        }
        return WinLose.TIE;
    }

    public long countDealerWins() {
        return resultMap.values().stream()
            .filter(value -> value == WinLose.LOSE)
            .count();
    }

    public long countDealerLoses() {
        return resultMap.values().stream()
            .filter(value -> value == WinLose.WIN)
            .count();
    }

    public long countDealerTies() {
        return resultMap.values().stream()
            .filter(value -> value == WinLose.TIE)
            .count();
    }

    public WinLose playerWinLose(Player player) {
        return resultMap.get(player);
    }
}
