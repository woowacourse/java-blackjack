package model.dto;

import java.util.List;

public record ParticipantWinning(DealerWinning dealerWinning, List<PlayerWinning> playersWinning) {
}
