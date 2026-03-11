package dto;

import domain.FinalResult;
import domain.TotalFinalResult;

import java.util.ArrayList;
import java.util.List;

public record TotalFinalResultsDto(
        List<String> totalResults
) {
    public static TotalFinalResultsDto from(TotalFinalResult totalFinalResult) {
        List<FinalResult> finalResults = totalFinalResult.getTotalResult();
        List<String> totalResults = new ArrayList<>();
        for (FinalResult finalResult : finalResults) {
            totalResults.add(String.format("%s: %s%n",
                    finalResult.getNameText(),
                    finalResult.getResultText()));
        }

        return new TotalFinalResultsDto(totalResults);
    }
}
