package dto;

import java.util.List;

public class GameResultDto {
    private final DealerResultDto dealerResult;
    private final List<PlayerResultDto> playerResults;

    public GameResultDto(DealerResultDto dealerResult, List<PlayerResultDto> playerResults) {
        this.dealerResult = dealerResult;
        this.playerResults = playerResults;
    }

    public DealerResultDto getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResultDto> getPlayerResults() {
        return playerResults;
    }
}