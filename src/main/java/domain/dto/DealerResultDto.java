package domain.dto;

import domain.constant.Result;

import java.util.Map;

import static domain.constant.Result.*;

public class DealerResultDto {

    private String name;
    private int winCount;
    private int drawCount;
    private int loseCount;

    private DealerResultDto(String name, int winCount, int drawCount, int loseCount) {
        this.name = name;
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.loseCount = loseCount;
    }

    public static DealerResultDto of(String name, Map<Result, Integer> dealerResult) {
        return new DealerResultDto(
                name,
                dealerResult.getOrDefault(WIN, 0),
                dealerResult.getOrDefault(DRAW, 0),
                dealerResult.getOrDefault(LOSE, 0)
        );
    }

    public String getName() {
        return name;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
