package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Results {

    private final Map<Participant, Result> values;

    public Results(final Dealer dealer, final Participants participants) {
        values = new LinkedHashMap<>();
        decideResults(dealer, participants);
    }

    private void decideResults(final Dealer dealer, final Participants participants) {
        int dealerScore = dealer.getTotalScore();
        for (Participant participant : participants) {
            int participantScore = participant.getTotalScore();
            values.put(participant, Result.decide(dealerScore, participantScore));
        }
    }

    public Map<Participant, Result> getValues() {
        return values;
    }
}
