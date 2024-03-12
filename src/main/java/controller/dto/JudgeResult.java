package controller.dto;

import java.util.List;

public record JudgeResult(
        List<PlayerOutcome> results,
        int winnerCount
) {
}
