package model.dto;

import java.util.List;
import model.DealerWinning;

public record ParticipantWinning(DealerWinning dealerWinning, List<PlayerWinning> playersWinning) {
}
