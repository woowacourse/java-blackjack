package domain;

import java.util.List;

public class PlayerResultConverter {

    public static List<PlayerResultDto> convert(BlackjackResult blackjackResult) {
        return blackjackResult.playerProfits().entrySet().stream()
                .map(entry -> new PlayerResultDto(
                        entry.getKey().getName(),
                        entry.getValue()
                ))
                .toList();
    }
}
