package dto;

import domain.participant.Participant;
import domain.result.BlackjackResult;
import java.util.EnumMap;
import java.util.Map;

public record ResultDto(
        EnumMap<BlackjackResult, Integer> dealerResult,
        Map<Participant, BlackjackResult> playerResults
) {
}
