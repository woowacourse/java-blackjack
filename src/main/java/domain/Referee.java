package domain;

import java.util.Map;
import java.util.stream.Collectors;

public class Referee {

    private final GameResultCalculator gameResultCalculator;

    public Referee(GameResultCalculator gameResultCalculator) {
        this.gameResultCalculator = gameResultCalculator;
    }

    public GameResults judge(Dealer dealer, Players players) {
        int dealerSum = dealer.calculateCardsSum();
        Map<Player, GameResultStatus> gameResult = players.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> gameResultCalculator.calculate(dealerSum, player.calculateCardsSum())
                ));
        return new GameResults(gameResult);
    }
}
