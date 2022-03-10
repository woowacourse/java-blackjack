package blackjack.dto;

import java.util.List;

public class TotalResultDTO {
    private List<PlayerResultDTO> totalPlayerResult;
    private DealerResultDTO dealerResult;

    public TotalResultDTO(List<PlayerResultDTO> totalPlayerResult, DealerResultDTO dealerResult) {
        this.totalPlayerResult = totalPlayerResult;
        this.dealerResult = dealerResult;
    }

    public List<PlayerResultDTO> getTotalPlayerResult() {
        return totalPlayerResult;
    }

    public DealerResultDTO getDealerResult() {
        return dealerResult;
    }
}
