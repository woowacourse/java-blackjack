package domain;

import java.util.Map;
import java.util.stream.Collectors;

public class Referee {

    public GameResult judge(Dealer dealer, Players players) {
        int dealerSum = dealer.calculateCardsSum();
        Map<Player, GameResultStatus> gameResult = players.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> GameResultStatus.calculate(dealerSum, player.calculateCardsSum())
                ));
        return new GameResult(gameResult);
    }
}
