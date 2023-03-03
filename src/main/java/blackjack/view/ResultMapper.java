package blackjack.view;

import blackjack.domain.Result;

import java.util.Arrays;
import java.util.List;

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

    public static String getDealerResult(List<Result> playerResults) {
        int winCount = getCount(playerResults, Result.LOSE);
        int drawCount = getCount(playerResults, Result.DRAW);
        int loseCount = getCount(playerResults, Result.WIN);

        return getResult(winCount, drawCount, loseCount);
    }

    private static String getResult(int winCount, int drawCount, int loseCount) {
        String result = "";
        if (winCount > 0) {
            result += winCount + WIN.value + " ";
        }
        if (drawCount > 0) {
            result += drawCount + DRAW.value + " ";
        }
        if (loseCount > 0) {
            result += loseCount + LOSE.value;
        }
        return result;
    }

    public static String map(Result result) {
        return Arrays.stream(values())
                .filter(resultMapper -> resultMapper.result.equals(result))
                .map(ResultMapper::getValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 결과가 없습니다."));
    }

    private static int getCount(List<Result> playerResults, Result result) {
        return (int) playerResults.stream()
                .filter(resultMapper -> resultMapper.equals(result))
                .count();
    }

    public String getValue() {
        return value;
    }
}
