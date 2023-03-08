package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Referee {

    public static List<Result> getPlayersResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        List<Result> playersResult = new ArrayList<>();
        for (Player player : players) {
            if (player.isBust()) {
                playersResult.add(new Result(player.getName(), WinResult.LOSE));
                continue;
            }
            playersResult.add(new Result(player.getName(), dealer.judge(player)));
        }
        return playersResult;
    }
}
