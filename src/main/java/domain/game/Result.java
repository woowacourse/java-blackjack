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
        Map<Player, WinLose> map = new LinkedHashMap<>();

        for (Player player : players) {
            WinLose winLose = decideWinLose(player, dealer);
            map.put(player, winLose);
        }

        return new Result(map);
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
        if (dealer.score() < player.score()) {
            return WinLose.WIN;
        }
        if (dealer.score() > player.score()) {
            return WinLose.LOSE;
        }
        return WinLose.TIE;
    }

    public WinLose playerWinLose(Player player) {
        return resultMap.get(player);
    }
}
