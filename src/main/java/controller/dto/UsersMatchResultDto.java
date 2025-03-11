package controller.dto;

import domain.player.Player;
import domain.stats.MatchResult;
import java.util.Map;
import java.util.stream.Collectors;

public record UsersMatchResultDto(
        Map<String, MatchResult> nameMatchResult
) {

    public static UsersMatchResultDto from(Map<Player, MatchResult> results) {
        return new UsersMatchResultDto(
                results.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                entry -> entry.getKey().getName(),
                                Map.Entry::getValue
                        ))
        );
    }
}
