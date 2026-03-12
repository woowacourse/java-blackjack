package domain.dto;

import domain.constant.Result;

public class GameResultDto {
    private final String playerName;
    private final Result result;
    private final double proceeds;

    public GameResultDto(String playerName, double proceeds) {
        this(playerName, null, proceeds);
    }

    public GameResultDto(String playerName, Result result, double proceeds) {
        this.playerName = playerName;
        this.result = result;
        this.proceeds = proceeds;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Result getResult() {
        return result;
    }

    public double getProceeds() {
        return proceeds;
    }

    @Override
    public String toString() {
        return "GameResultDto{" +
                "playerName='" + playerName + '\'' +
                ", result=" + result +
                ", proceeds=" + proceeds +
                '}';
    }
}