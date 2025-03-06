package domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackReferee {
    public GameResult judge(Dealer dealer, List<Player> players) {
        int dealerSum = dealer.calculateCardsSum();
        Map<Player, GameResultStatus> gameResult = players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> GameResultStatus.calculate(dealerSum, player.calculateCardsSum())
                ));
        return new GameResult(gameResult);
    }
}
