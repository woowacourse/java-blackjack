package blackjack.controller.dto;

import blackjack.domain.gamer.Gamer;
import blackjack.domain.money.Profit;

import java.util.Collections;
import java.util.Map;

public final class GamersResultDto {

    private final Map<Gamer, Profit> gamersResult;

    public GamersResultDto(Map<Gamer, Profit> gamersResult) {
        validate(gamersResult);
        this.gamersResult = Collections.unmodifiableMap(gamersResult);
    }

    private void validate(Map<Gamer, Profit> gamersResult) {
        if (gamersResult == null || gamersResult.isEmpty()) {
            throw new IllegalArgumentException("결과가 없습니다.");
        }
    }

    public Map<Gamer, Profit> getGamersResult() {
        return gamersResult;
    }
}