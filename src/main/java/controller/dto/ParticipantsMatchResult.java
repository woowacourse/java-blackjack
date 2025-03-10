package controller.dto;

import domain.MatchResult;
import domain.Player;
import java.util.Map;

public record ParticipantsMatchResult(
        Map<Player, MatchResult> participantMatchResult
) {

}
