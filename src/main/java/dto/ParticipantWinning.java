package dto;

import model.DealerWinning;
import model.PlayersWinning;

public record ParticipantWinning(DealerWinning dealerWinning, PlayersWinning playersWinning) {
}
