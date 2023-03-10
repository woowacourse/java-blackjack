package blackjack.view.dto;

import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;

public class PlayerResultResponse {

    private final String name;
    private final Result result;

    private PlayerResultResponse(final String name, final Result result) {
        this.name = name;
        this.result = result;
    }

    public static PlayerResultResponse of(final Player player, final Result result) {
        return new PlayerResultResponse(player.getName(), result);
    }

    public String getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }
}
