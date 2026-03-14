package dto;

import java.math.BigDecimal;
import java.util.List;

public record ParticipantWinningResponse(BigDecimal dealerProfit, List<PlayerProfitResponse> playersWinning) {
    public ParticipantWinningResponse {
        playersWinning = List.copyOf(playersWinning);
    }
}
