package dto;

import java.util.List;

public class BettingResultsDto {
    private final List<BettingResultDto> bettingResults;

    public BettingResultsDto(List<BettingResultDto> bettingResults) {
        this.bettingResults = bettingResults;
    }

    public List<BettingResultDto> getBettingResults() {
        return bettingResults;
    }
}
