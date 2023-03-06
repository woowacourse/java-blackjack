package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGameResult {
    private final Map<Participant, Result> dealerResultsAgainstParticipants;

    private BlackjackGameResult(Map<Participant, Result> dealerResultsAgainstParticipants) {
        this.dealerResultsAgainstParticipants = dealerResultsAgainstParticipants;
    }

    public static BlackjackGameResult from(BlackjackGame blackjackGame) {
        Map<Participant, Result> dealerResultsAgainstParticipants = new LinkedHashMap<>();
        Participant dealer = blackjackGame.getDealer();
        List<Participant> players = blackjackGame.getPlayers();

        for (Participant player : players) {
            Result dealerResult = dealer.competeWith(player);
            dealerResultsAgainstParticipants.put(player, dealerResult);
        }

        return new BlackjackGameResult(dealerResultsAgainstParticipants);
    }

    public int getResultCount(Result result) {
        return (int) dealerResultsAgainstParticipants.values().stream()
                .filter(result::equals)
                .count();
    }

    public Map<Participant, Result> getDealerResultsAgainstParticipants() {
        return Collections.unmodifiableMap(dealerResultsAgainstParticipants);
    }
}
