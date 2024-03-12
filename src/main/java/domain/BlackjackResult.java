package domain;

import domain.participant.dealer.DealerResult;
import domain.participant.player.PlayerResults;

public record BlackjackResult(DealerResult dealerResult, PlayerResults playerResults) {
}
