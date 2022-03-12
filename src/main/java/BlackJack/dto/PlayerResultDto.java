package BlackJack.dto;

import BlackJack.domain.Result;

public class PlayerResultDto {

    private final String name;
    private final String result;

    public PlayerResultDto(String name, Result result) {
        this.name = name;
        this.result = result.getValue();
    }

    public static PlayerResultDto from(String name, Result compare) {
        return new PlayerResultDto(name, compare);
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }
}

