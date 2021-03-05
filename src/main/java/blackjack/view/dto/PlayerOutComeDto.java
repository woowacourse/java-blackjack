package blackjack.view.dto;

import blackjack.domain.Outcome;

public class PlayerOutComeDto {
    private final String playerName;
    private final Outcome outcome;

    public PlayerOutComeDto(String playerName, Outcome outcome) {
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
