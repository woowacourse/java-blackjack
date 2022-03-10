package blackjack.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Results {

    private final Map<Participant, Result> values;

    public Results(final Dealer dealer, final List<Participant> participants) {
        values = new LinkedHashMap<>();
        decideResults(dealer, participants);
    }

    private void decideResults(final Dealer dealer, final List<Participant> participants) {
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
