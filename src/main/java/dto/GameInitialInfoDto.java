package dto;

import java.util.List;

public class GameInitialInfoDto {

    private String playerName;
    private List<String> hand;

    public GameInitialInfoDto(String playerName, List<String> hand) {
        this.playerName = playerName;
        this.hand = hand;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getHand() {
        return hand;
    }
}
