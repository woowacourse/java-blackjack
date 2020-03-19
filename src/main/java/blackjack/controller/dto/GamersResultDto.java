package blackjack.controller.dto;

import blackjack.domain.gamer.Gamer;

import java.util.Map;

public final class GamersResultDto {

    private final Map<Gamer, Integer> playersResult;

    public GamersResultDto(Map<Gamer, Integer> playersResult) {
        this.playersResult = playersResult;
    }

    public Map<Gamer, Integer> getPlayersResult() {
        return playersResult;
    }
}