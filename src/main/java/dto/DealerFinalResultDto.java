package dto;

import domain.ResultType;
import domain.TotalFinalResult;

public record DealerFinalResultDto(
        int winCount,
        int drawCount,
        int loseCount
) {
    public static DealerFinalResultDto from(TotalFinalResult totalFinalResult) {
        return new DealerFinalResultDto(countResult(totalFinalResult, ResultType.LOSE),
                countResult(totalFinalResult, ResultType.DRAW),
                countResult(totalFinalResult, ResultType.WIN));
    }

    private static int countResult(TotalFinalResult totalFinalResult, ResultType resultType) {
        return (int) totalFinalResult.getTotalResult().stream()
                .filter(finalResult -> finalResult.getResultType().equals(resultType))
                .count();
    }


}
