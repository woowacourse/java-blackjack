package blackjack.dto;

import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;

public record PlayerNameFinalResult(String name, ResultCommand result) {

    public static PlayerNameFinalResult from(final Player player, final ResultCommand result) {
        return new PlayerNameFinalResult(player.getName(), result);
    }
}
