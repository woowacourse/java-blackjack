package dto;

import java.util.List;

public class GameScoreResultDto {
    final String playerName;
    final List<String> hand;
    final int result;

    public GameScoreResultDto(String playerName, List<String> hand, int result) {
        this.playerName = playerName;
        this.hand = hand;
        this.result = result;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getHand() {
        return hand;
    }

    public int getResult() {
        return result;
    }
}
