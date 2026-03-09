package domain.dto;

import domain.constant.Result;

public class GameFinalResultDto {
    String name;
    Result result;
    // TODO: 베팅 기능 추가 시 베팅 금액, 정산 금액(수익/손실) 필드 추가 필요


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
