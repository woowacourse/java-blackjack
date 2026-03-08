package domain.dto;

import domain.Player;

import java.util.List;

public class GameScoreResultDto {
    String playerName;
    List<String> hand;
    int score;

    private GameScoreResultDto(String playerName, List<String> hand, int result) {
        this.playerName = playerName;
        this.hand = hand;
        this.score = result;
    }

    public static GameScoreResultDto from(Player player) {
        return new GameScoreResultDto(
                player.getName(),
                player.showHand(),
                player.getScore()
        );
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "domain.dto.GameScoreResultDto{" +
                "playerName='" + playerName + '\'' +
                ", hand=" + hand +
                ", result=" + score +
                '}';
    }
}
