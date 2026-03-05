package dto;

import java.util.List;

public record BlackjackResult(
        int winCount,
        int lossCount,
        List<String> logs
) {
}
