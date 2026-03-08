package dto;

import domain.ResultType;
import domain.TotalFinalResult;

public record DealerFinalResultDto(
        String result
) {
    public static DealerFinalResultDto from(TotalFinalResult totalFinalResult) {
        return new DealerFinalResultDto(checkDrawCount(countResult(totalFinalResult, ResultType.LOSE),
                countResult(totalFinalResult, ResultType.DRAW),
                countResult(totalFinalResult, ResultType.WIN)));
    }

    private static int countResult(TotalFinalResult totalFinalResult, ResultType resultType) {
        return (int) totalFinalResult.getTotalResult().stream()
                .filter(finalResult -> finalResult.getResultType().equals(resultType))
                .count();
    }

    private static String checkDrawCount(int winCount, int drawCount, int loseCount) {
        if (drawCount > 0) {
            return String.format("딜러: %d승 %d무 %d패%n", winCount, drawCount, loseCount);
        }
        return String.format("딜러: %d승 %d패%n", winCount, loseCount);
    }
}
