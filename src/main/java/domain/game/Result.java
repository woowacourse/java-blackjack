package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.Collections;
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
        return compareScore(player, dealer);
    }

    private static WinLose compareScore(Player player, Dealer dealer) {
        if (dealer.score() > player.score()) {
            return WinLose.LOSE;
        }
        if (dealer.score() < player.score()) {
            return WinLose.WIN;
        }
        return WinLose.TIE;
    }

    public Map<Player, WinLose> getResultMap() {
        return Collections.unmodifiableMap(resultMap);
    }
}
