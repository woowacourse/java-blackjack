package dto;

import domain.FinalResult;
import domain.TotalFinalResult;
import java.util.ArrayList;
import java.util.List;

public record TotalFinalResultsDto(
        List<FinalResultDto> totalResults
) {
    public static TotalFinalResultsDto from(TotalFinalResult totalFinalResult) {
        List<FinalResult> finalResults = totalFinalResult.getTotalResult();
        List<FinalResultDto> totalResults = new ArrayList<>();
        for (FinalResult finalResult : finalResults) {
            totalResults.add(FinalResultDto.from(finalResult));
        }

        return new TotalFinalResultsDto(totalResults);
    }
}
