package domain;

import java.util.Map;
import java.util.stream.Collectors;

public class Referee {

    public GameResults judge(Dealer dealer, Players players) {
        Map<Player, GameResultStatus> gameResult = players.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.calculateResultStatus(dealer.getCards())
                ));
        return new GameResults(gameResult);
    }
}
