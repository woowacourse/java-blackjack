package domain;

import java.util.List;

public record ParticipantsResult(DealerResult dealerResult, List<PlayerResult> playerResults) {

}
