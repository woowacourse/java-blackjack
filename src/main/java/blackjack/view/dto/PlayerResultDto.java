package blackjack.view.dto;

import blackjack.domain.Outcome;

public class PlayerResultDto {
    private final String playerName;
    private final Outcome outcome;

    public PlayerResultDto(String playerName, Outcome outcome) {
        this.playerName = playerName;
        this.outcome = outcome;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Outcome getOutcome() {
        return outcome;
    }
}
