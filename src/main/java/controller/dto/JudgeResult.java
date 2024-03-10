package controller.dto;

import java.util.List;

public record JudgeResult(
        List<ParticipantOutcome> results
) {
}
