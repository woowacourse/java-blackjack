package result;

import java.util.List;
import participant.Dealer;
import participant.Player;
import result.handResult.BlackJack;
import result.handResult.Bust;
import result.handResult.HandResult;
import result.handResult.ScoreComparison;

public class GameResult {
    private static final List<HandResult> HAND_RESULTS = List.of(
            new Bust(),
            new BlackJack(),
            new ScoreComparison()
    );

    public static GameStatus calculate(Player player, Dealer dealer) {
        return HAND_RESULTS.stream()
                .map(handResult -> handResult.calculate(player, dealer))
                .filter(GameStatus::isDecided)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
