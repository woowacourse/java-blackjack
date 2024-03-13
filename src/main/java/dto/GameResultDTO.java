package dto;

import java.util.List;

public class GameResultDTO {
    private final List<String> playersName;
    private final List<Integer> playersEarnMoney;

    public GameResultDTO(List<String> playersName, List<Integer> playersEarnMoney) {
        this.playersName = playersName;
        this.playersEarnMoney = playersEarnMoney;
    }

    public List<String> getPlayersName() {
        return playersName;
    }

    public List<Integer> getPlayersEarnMoney() {
        return playersEarnMoney;
    }
}
