package dto;

import java.math.BigDecimal;
import java.util.List;

public record ParticipantWinning(BigDecimal dealerProfit, List<PlayerProfit> playersWinning) {
    public ParticipantWinning {
        playersWinning = List.copyOf(playersWinning);
    }
}
