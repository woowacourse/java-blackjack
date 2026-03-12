package domain.dto;

import domain.constant.Result;

public class GameResultDto {
    String playerName;
    Result result;
    // TODO: 베팅 기능 추가 시 베팅 금액, 정산 금액(수익/손실) 필드 추가 필요


    public GameResultDto(String playerName) {
        this(playerName, null);
    }

    public GameResultDto(String playerName, Result result) {
        this.playerName = playerName;
        this.result = result;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "domain.dto.GameFinalResultDto{" +
                "name='" + playerName + '\'' +
                ", result=" + result +
                '}';
    }
}
