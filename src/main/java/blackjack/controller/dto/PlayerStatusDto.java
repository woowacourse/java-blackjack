package blackjack.controller.dto;

import java.util.List;

public class PlayerStatusDto {
    private final String playerName;
    private final List<String> playerCardStatus;
    private final int playerScore;

    public PlayerStatusDto(String playerName, List<String> playerCardStatus, int playerScore) {
        this.playerName = playerName;
        this.playerCardStatus = playerCardStatus;
        this.playerScore = playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getPlayerCardStatus() {
        return playerCardStatus;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
