import java.util.Map;

public record GameResult(
        Map<ScoreCompareResult, Integer> dealerResult,
        Map<Player, ScoreCompareResult> playerResults
) {
}
