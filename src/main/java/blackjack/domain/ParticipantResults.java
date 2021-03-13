package blackjack.domain;

import blackjack.domain.participants.Name;
import blackjack.domain.participants.Participant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParticipantResults {

    private final Participant dealer;
    private final List<Participant> players;
    private final Map<Name, Integer> results;

    public ParticipantResults(final Participant dealer, final List<Participant> players) {
        this.dealer = dealer;
        this.players = players;
        results = makeParticipantResults();
    }

    public Map<Name, Integer> makeParticipantResults() {
        final Map<Name, Integer> participantResults = new LinkedHashMap<>();
        participantResults.put(dealer.getName(), -calculateTotalPlayersRate());

        for (final Participant player : players) {
            final Result result = player.decideWinner(dealer);
            participantResults.put(player.getName(), (int) result.calculateRate(player.getMoney()));
        }
        return participantResults;
    }

    public int calculateTotalPlayersRate() {
        double total = 0;
        for (final Participant player : players) {
            final Result result = player.decideWinner(dealer);
            total += result.calculateRate(player.getMoney());
        }
        return (int) total;
    }

    public Map<Name, Integer> getResults() {
        return results;
    }

}
