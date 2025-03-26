package domain;

import domain.result.DealerResults;
import domain.result.PlayerResults;

public record ParticipantsResult(DealerResults dealerResults, PlayerResults playerResults) {

}
