package domain.dto;

import java.util.List;

public class GameScoreResultDto {
    String playerName;
    List<String> hand;
    int result;

    public GameScoreResultDto(String playerName, List<String> hand, int result) {
        this.playerName = playerName;
        this.hand = hand;
        this.result = result;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<String> getHand() {
        return hand;
    }

    public void setHand(List<String> hand) {
        this.hand = hand;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "domain.dto.GameScoreResultDto{" +
                "playerName='" + playerName + '\'' +
                ", hand=" + hand +
                ", result=" + result +
                '}';
    }
}
