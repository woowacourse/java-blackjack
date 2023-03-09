package blackjack.view;

import blackjack.domain.game.Result;

import java.util.Arrays;
import java.util.Map;

public enum ResultMapper {

    WIN(Result.WIN, "승"),
    DRAW(Result.DRAW, "무"),
    LOSE(Result.LOSE, "패");

    private final Result result;
    private final String value;

    ResultMapper(Result result, String value) {
        this.result = result;
        this.value = value;
    }

    public static String getDealerResult(Map<Result, Integer> dealerResults) {
        StringBuilder stringBuilder = new StringBuilder();
        ResultMapper[] resultMappers = ResultMapper.values();
        for (ResultMapper resultMapper : resultMappers) {
            stringBuilder.append(resultCount(dealerResults, resultMapper));
        }
        return stringBuilder.toString();
    }

    private static String resultCount(Map<Result, Integer> results, ResultMapper resultMapper) {
        if (results.containsKey(resultMapper.result)) {
            return results.get(resultMapper.result) + resultMapper.value + " ";
        }
        return "";
    }

    public static String map(Result result) {
        return Arrays.stream(values())
                .filter(resultMapper -> resultMapper.result.equals(result))
                .map(ResultMapper::getValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 결과가 없습니다."));
    }

    public String getValue() {
        return value;
    }
}
