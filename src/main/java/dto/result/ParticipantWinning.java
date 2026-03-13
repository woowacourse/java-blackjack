package dto.result;

import model.result.DealerWinning;
import model.result.PlayersWinning;

public record ParticipantWinning(DealerWinning dealerWinning, PlayersWinning playersWinning) {}
