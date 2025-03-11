package controller.dto;

import domain.player.Player;
import java.util.List;
import java.util.Map;

public record NameAndSumsDto(
        List<NameAndSumDto> nameAndSums
) {

    public static NameAndSumsDto from(Map<Player, Integer> playerSum) {
        return new NameAndSumsDto(
                playerSum.entrySet().stream()
                        .map(entry -> new NameAndSumDto(entry.getKey().getName(), entry.getValue()))
                        .toList()
        );
    }

    public record NameAndSumDto(
            String name,
            int sum
    ) {

    }
}
