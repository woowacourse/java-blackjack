package dto;

import java.util.List;

public record BlackjackResult(
        int winCount,
        int lossCount,
        int drawCount,
        List<GamblerResultLog> gamblerResultLog
) {
}
