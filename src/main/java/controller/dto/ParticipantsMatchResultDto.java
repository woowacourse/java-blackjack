package controller.dto;

import domain.MatchResult;
import domain.Player;
import java.util.Map;

public record ParticipantsMatchResultDto(
        Map<Player, MatchResult> participantMatchResult
) {

    public static ParticipantsMatchResultDto from(Map<Player, MatchResult> results) {
        return new ParticipantsMatchResultDto(results);
    }
}
