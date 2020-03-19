package blackjack.controller.dto;

import blackjack.domain.gamer.Gamer;
import blackjack.domain.money.Profit;

import java.util.Map;

public final class GamersResultDto {

    private final Map<Gamer, Profit> playersResult;

    public GamersResultDto(Map<Gamer, Profit> playersResult) {
        this.playersResult = playersResult;
    }

    public Map<Gamer, Profit> getPlayersResult() {
        return playersResult;
    }
}