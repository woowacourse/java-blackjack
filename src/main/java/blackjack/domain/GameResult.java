package blackjack.domain;

import java.util.Map;

public record GameResult(
        Map<ScoreCompareResult, Integer> dealerResult,
        Map<Participant, ScoreCompareResult> playerResults
) {
}
