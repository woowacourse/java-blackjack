package blackjack.dto;

import java.util.List;

public class TotalResultDto {
    private List<PlayerResultDto> totalPlayerResult;
    private DealerResultDto dealerResult;

    public TotalResultDto(List<PlayerResultDto> totalPlayerResult, DealerResultDto dealerResult) {
        this.totalPlayerResult = totalPlayerResult;
        this.dealerResult = dealerResult;
    }

    public List<PlayerResultDto> getTotalPlayerResult() {
        return totalPlayerResult;
    }

    public DealerResultDto getDealerResult() {
        return dealerResult;
    }
}
