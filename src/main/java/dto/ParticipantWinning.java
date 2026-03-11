package dto;

import java.util.List;

public record ParticipantWinning(int dealerProfit, List<PlayerWinning> playersWinning) {
    public ParticipantWinning {
        playersWinning = List.copyOf(playersWinning);
    }
}
