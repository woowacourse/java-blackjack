package blackjack.controller;

import blackjack.domain.DrawDecision;
import blackjack.domain.PlayerName;

import java.util.Arrays;
import java.util.List;

public class InputMapper {

    private static final String DELIMITER = ",";

    public List<PlayerName> mapToPlayers(String target) {
        String[] split = target.split(DELIMITER);
        return Arrays.stream(split)
                .map(PlayerName::new)
                .toList();
    }

    public DrawDecision mapToDrawDecision(String target) {
        return DrawDecision.from(target);
    }
}
