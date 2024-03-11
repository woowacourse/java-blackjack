package controller.dto;

import static domain.constants.Outcome.WIN;

import java.util.List;

public record JudgeResult(
        List<PlayerOutcome> results
) {
    // TODO: 출력을 위해 count 하는 로직 정도는 들어가도 되지 않나?
    public int countWinner() {
        return (int) results.stream()
                .filter(result -> WIN.equals(result.outcome()))
                .count();
    }
}
