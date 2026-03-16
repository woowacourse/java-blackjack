package dto;

import domain.constant.Result;

public class PlayerResultDto {
    private final String playerName;
    private final Result result;
    private final double proceeds;


    public PlayerResultDto(String playerName, Result result, double proceeds) {
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