package blackjack.domain.participant.player;

import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Name;

public class PlayerWinningDto {
    private final Name name;
    private final WinningResult winningResult;

    private PlayerWinningDto(Name name, WinningResult winningResult) {
        this.name = name;
        this.winningResult = winningResult;
    }

    public static PlayerWinningDto from(Player player) {
        return new PlayerWinningDto(player.getName(), player.getResult());
    }

    public Name getName() {
        return name;
    }

    public WinningResult getResult() {
        return winningResult;
    }
}
