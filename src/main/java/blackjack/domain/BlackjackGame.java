package blackjack.domain;

import blackjack.domain.participants.Name;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final Participant dealer;
    private final List<Participant> players;

    public BlackjackGame(final Participants participants) {
        this.dealer = participants.getDealer();
        this.players = new ArrayList<>(participants.getPlayers());
    }

    public BlackjackGame(final Participant dealer, final List<Participant> players) {
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public ParticipantResult makeParticipantResults() {
        final Map<Name, Integer> participantResults = new LinkedHashMap<>();
        participantResults.put(dealer.getName(), -calculateTotalPlayersRate());

        for (final Participant player : players) {
            final Result result = player.decideWinner(dealer);
            participantResults.put(player.getName(), (int) result.calculateRate(player.getMoney()));
        }
        return new ParticipantResult(participantResults);
    }

    public int calculateTotalPlayersRate() {
        double total = 0;
        for (final Participant player : players) {
            final Result result = player.decideWinner(dealer);
            total += result.calculateRate(player.getMoney());
        }
        return (int) total;
    }

}
