package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {

    public Referee() {
    }

    public Map<Player, GameResult> judge(Dealer dealer, Players players) {
        Map<Player, GameResult> judgeMap = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult playerResult = GameResult.judge(dealer, player);
            judgeMap.put(player, playerResult);
        }
        return Collections.unmodifiableMap(judgeMap);
    }


}
