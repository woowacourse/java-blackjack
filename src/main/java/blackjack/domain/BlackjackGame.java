package blackjack.domain;

import blackjack.domain.participants.Name;
import blackjack.domain.participants.Participant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    public Map<Name, Integer> makeParticipantResults(final Participant dealer,
        final List<Participant> players) {
        final Map<Name, Integer> participantResults = new LinkedHashMap<>();
        participantResults.put(dealer.getName(), -calculateTotalPlayersRate(dealer, players));
        for (final Participant player : players) {
            final Result result = dealer.decideWinner(player);
            participantResults.put(player.getName(), (int) result.calculateRate(player.getMoney()));
        }
        return participantResults;
    }

    public int calculateTotalPlayersRate(final Participant dealer,
        final List<Participant> players) {
        double total = 0;
        for (final Participant player : players) {
            final Result result = dealer.decideWinner(player);
            total += result.calculateRate(player.getMoney());
        }
        return (int) total;
    }

}
