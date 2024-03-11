package controller.dto;

import static domain.constants.Outcome.WIN;

import java.util.List;

public record JudgeResult(
        List<PlayerOutcome> results
) {
    public int countWinner() {
        return (int) results.stream()
                .filter(result -> WIN.equals(result.outcome()))
                .count();
    }
}
