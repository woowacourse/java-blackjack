package domain.dto;

import domain.constant.Result;

public class GameFinalResultDto {
    String name;
    Result result;

    public GameFinalResultDto(String name) {
        this(name, null);
    }

    public GameFinalResultDto(String name, Result result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "domain.dto.GameFinalResultDto{" +
                "name='" + name + '\'' +
                ", result=" + result +
                '}';
    }
}
