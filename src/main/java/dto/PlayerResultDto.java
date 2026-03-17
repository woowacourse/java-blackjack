package dto;

import domain.result.Result;
import domain.result.Results;
import java.util.List;

public record PlayerResultDto(String name, long profit) {

    public static PlayerResultDto from(Result result) {
        String name = result.getPlayer().getNameString();
        long profit = result.getProfit().money().amount(); // todo 디미터의 법칙 적용
        return new PlayerResultDto(name, profit);
    }

    public static List<PlayerResultDto> fromResults(Results results) {
        return results.results().stream()
                .map(PlayerResultDto::from)
                .toList();
    }
}
