package controller.dto;

import domain.Player;
import java.util.List;
import java.util.Map;

public record NameAndSums(
        List<NameAndSum> nameAndSums
) {

    public static NameAndSums from(Map<Player, Integer> playerSum) {
        return new NameAndSums(
                playerSum.entrySet().stream()
                        .map(entry -> new NameAndSum(entry.getKey().getName(), entry.getValue()))
                        .toList()
        );
    }

    public record NameAndSum(
            String name,
            int sum
    ) {

    }
}
