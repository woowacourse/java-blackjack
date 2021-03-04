package blackjack.view.dto;

import java.util.List;

public class PlayerStatusDto {
    private final String playerName;
    private final List<String> playerCardStatus;

    public PlayerStatusDto(String playerName, List<String> playerCardStatus) {
        this.playerName = playerName;
        this.playerCardStatus = playerCardStatus;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getPlayerCardStatus() {
        return playerCardStatus;
    }
}
