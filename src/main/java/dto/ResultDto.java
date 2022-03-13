package dto;

import domain.participant.Result;

import java.util.EnumMap;

public class ResultDto {

    private final EnumMap<Result, Integer> resultCounts;
    private final String dealerName;

    private ResultDto(String name, EnumMap<Result, Integer> resultCounts) {
        this.dealerName = name;
        this.resultCounts = resultCounts;
    }

    public static ResultDto of(String name, int winCount, int drawCount, int loseCount) {
        EnumMap<Result, Integer> resultCounts = new EnumMap<Result, Integer>(Result.class);
        resultCounts.put(Result.WIN, winCount);
        resultCounts.put(Result.DRAW, drawCount);
        resultCounts.put(Result.LOSE, loseCount);

        return new ResultDto(name, resultCounts);
    }

    public String getDealerName() {
        return dealerName;
    }

    public int getWinCount() {
        return resultCounts.get(Result.WIN);
    }

    public int getDrawCount() {
        return resultCounts.get(Result.DRAW);
    }

    public int getLoseCount() {
        return resultCounts.get(Result.LOSE);
    }
}
