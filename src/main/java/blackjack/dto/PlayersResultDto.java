package blackjack.dto;

import blackjack.domain.participant.Player;
import blackjack.domain.result.MatchResult;

import java.util.Map;

public class PlayersResultDto {
    private final Map<Player, MatchResult> result;

    public PlayersResultDto(Map<Player, MatchResult> result) {
        this.result = result;
    }

    public Map<Player, MatchResult> getResult() {
        return result;
    }
}
