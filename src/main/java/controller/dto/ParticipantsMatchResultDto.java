package controller.dto;

import domain.MatchResult;
import domain.Player;
import java.util.Map;
import java.util.stream.Collectors;

public record ParticipantsMatchResultDto(
        Map<String, MatchResult> nameMatchResult
) {

    public static ParticipantsMatchResultDto from(Map<Player, MatchResult> results) {
        return new ParticipantsMatchResultDto(
                results.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                entry -> entry.getKey().getName(),
                                Map.Entry::getValue
                        ))
        );
    }
}
