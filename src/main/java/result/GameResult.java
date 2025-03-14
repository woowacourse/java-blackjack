package result;

import java.util.List;
import participant.Dealer;
import participant.Player;
import result.handStatus.BlackJack;
import result.handStatus.Bust;
import result.handStatus.HandStatus;
import result.handStatus.ScoreComparison;

public class GameResult {
    private final List<HandStatus> handStatuses;

    public GameResult() {
        this.handStatuses = List.of(
                new Bust(),
                new BlackJack(),
                new ScoreComparison()
        );
    }

    public GameStatus calculate(Player player, Dealer dealer) {
        return handStatuses.stream()
                .map(handStatus -> handStatus.calculateResult(player, dealer))
                .filter(GameStatus::isDecided)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
