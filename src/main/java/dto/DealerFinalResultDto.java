package dto;

import domain.FinalResult;
import domain.ResultType;
import domain.TotalFinalResult;

public record DealerFinalResultDto(
        String result
) {
    public static DealerFinalResultDto from(TotalFinalResult totalFinalResult) {
        int winCount = 0;
        int drawCount = 0;
        int loseCount = 0;

        for (FinalResult finalResult : totalFinalResult.getTotalResult()) {
            if (finalResult.getResultType().equals(ResultType.WIN)) {
                loseCount++;
                continue;
            }
            if (finalResult.getResultType().equals(ResultType.DRAW)) {
                drawCount++;
                continue;
            }
            winCount++;
        }

        return new DealerFinalResultDto(checkDrawCount(winCount, drawCount, loseCount));
    }

    private static String checkDrawCount(int winCount, int drawCount, int loseCount) {
        if (drawCount > 0) {
            return String.format("딜러: %d승 %d무 %d패%n", winCount, drawCount, loseCount);
        }
        return String.format("딜러: %d승 %d패%n", winCount, loseCount);
    }
}
