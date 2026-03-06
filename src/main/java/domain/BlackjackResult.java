package domain;

import domain.dto.BlackjackResultDto;

import java.util.HashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<Player, Boolean> playerWinningMap = new HashMap<>();

    private BlackjackResult() {
    }

    public static BlackjackResult from(Dealer dealer, Players players) {
        return new  BlackjackResult();
    }

    public BlackjackResultDto toResultDto() {
        return new BlackjackResultDto(0, 0);
    }
}
