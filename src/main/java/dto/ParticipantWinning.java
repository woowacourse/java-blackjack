package dto;

import java.util.List;

public record ParticipantWinning(double dealerProfit, List<PlayerProfit> playersWinning) {
    public ParticipantWinning {
        playersWinning = List.copyOf(playersWinning);
    }
}
