package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public record GameResult(
        Map<ScoreCompareResult, Integer> dealerResult,
        LinkedHashMap<Player, ScoreCompareResult> playerResults
) {
}
