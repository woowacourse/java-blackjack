package blackjack.domain;

import java.util.Map;

public record GameResult(
        Map<Participant, ScoreCompareResult> playerResults
) {
}
