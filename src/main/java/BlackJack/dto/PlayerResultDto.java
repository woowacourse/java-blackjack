package BlackJack.dto;

import BlackJack.domain.PlayerScore;
import BlackJack.domain.Result;

public class PlayerResultDto {

    private String name;
    private String result;

    public PlayerResultDto(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public static PlayerResultDto from(String name, Result result) {
        return new PlayerResultDto(name, result.getValue());
    }


    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }
}

