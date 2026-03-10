package dto;

import model.MatchStatus;
import java.util.List;
import java.util.Map;

public record ParticipantWinning(int dealerWinning, List<PlayerWinning> playersWinning) {
    public ParticipantWinning {
        playersWinning = List.copyOf(playersWinning);
    }
}
