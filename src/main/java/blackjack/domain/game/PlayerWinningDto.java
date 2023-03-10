package blackjack.domain.game;

import blackjack.domain.participant.Name;

public class PlayerWinningDto {
    private final Name name;
    private final WinningResult winningResult;

    public PlayerWinningDto(Name name, WinningResult winningResult) {
        this.name = name;
        this.winningResult = winningResult;
    }

    public Name getName() {
        return name;
    }

    public WinningResult getResult() {
        return winningResult;
    }
}
