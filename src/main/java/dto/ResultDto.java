package dto;

import domain.BlackjackResult;
import domain.Participant;
import java.util.EnumMap;
import java.util.Map;

public record ResultDto(
        EnumMap<BlackjackResult, Integer> dealerResult,
        Map<Participant, BlackjackResult> playerResults
) {
}
