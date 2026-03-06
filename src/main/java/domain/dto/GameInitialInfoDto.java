package domain.dto;

import java.util.List;

public class GameInitialInfoDto {

    private String playerName;
    private int initialHandSize;
    private List<String> hand;

    public GameInitialInfoDto(String playerName, int initialHandSize, List<String> hand) {
        this.playerName = playerName;
        this.initialHandSize = initialHandSize;
        this.hand = hand;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getInitialHandSize() {
        return initialHandSize;
    }

    public List<String> getHand() {
        return hand;
    }
}
