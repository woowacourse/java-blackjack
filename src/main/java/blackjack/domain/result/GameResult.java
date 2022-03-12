package blackjack.domain.result;

import java.util.List;

public class GameResult {

    private final DealerResult dealerResult;
    private final List<ParticipantResult> participantResults;

    public GameResult(DealerResult dealerResult, List<ParticipantResult> participantResults) {
        this.dealerResult = dealerResult;
        this.participantResults = participantResults;
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public List<ParticipantResult> getParticipantResults() {
        return participantResults;
    }
}
