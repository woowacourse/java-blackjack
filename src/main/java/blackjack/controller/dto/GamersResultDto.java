package blackjack.controller.dto;

import blackjack.domain.gamer.Gamer;
import blackjack.domain.money.Profit;

import java.util.Map;

public final class GamersResultDto {

    private final Map<Gamer, Profit> gamersResult;

    public GamersResultDto(Map<Gamer, Profit> gamersResult) {
        validate(gamersResult);
        this.gamersResult = gamersResult;
    }

    private void validate(Map<Gamer, Profit> gamersResult) {
        if (gamersResult == null || gamersResult.isEmpty()) {
            throw new IllegalArgumentException("플레이어 결과가 텅~ 비었네요!");
        }
    }

    public Map<Gamer, Profit> getGamersResult() {
        return gamersResult;
    }
}