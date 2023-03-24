package controller.mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import domain.Result;

public class ResultMapper {
    private static final Map<Result, String> RESULT_MAP = Map.ofEntries(
            Map.entry(Result.WIN, "승"),
            Map.entry(Result.WIN_BY_BLACKJACK, "승"),
            Map.entry(Result.DRAW, "무"),
            Map.entry(Result.LOSE, "패")
    );

    public static List<String> map(List<Result> results) {
        return results.stream()
                .map(ResultMapper::map)
                .collect(Collectors.toList());
    }

    public static String map(Result result) {
        String resultString = RESULT_MAP.get(result);
        if (Objects.isNull(resultString)) {
            throw new IllegalArgumentException("알 수 없는 게임 결과가 있습니다");
        }
        return resultString;
    }
}
