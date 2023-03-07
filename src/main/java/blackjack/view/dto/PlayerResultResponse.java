package blackjack.view.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Result;

public class PlayerResultResponse {

    private final String name;
    private final Result result;

    private PlayerResultResponse(final String name, final Result result) {
        this.name = name;
        this.result = result;
    }

    public static PlayerResultResponse of(final Dealer dealer, final Player player) {
        return new PlayerResultResponse(player.getName(), dealer.showResult(player.getScore()));
    }

    public String getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }
}
