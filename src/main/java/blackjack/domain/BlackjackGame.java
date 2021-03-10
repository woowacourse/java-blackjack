package blackjack.domain;

import blackjack.domain.participants.Name;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {

    public GameResult createGameResult(final Participants participants) {
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();
        final List<Integer> dealerCounts = makeDealerMatchCounts(dealer, players);
        final Map<Name, Result> playerResults = makePlayerResults(players, dealer);
        return new GameResult(dealerCounts, playerResults);
    }

    public List<Integer> makeDealerMatchCounts(final Participant dealer,
        final List<Participant> players) {
        final List<Result> dealerResults = Arrays.stream(Result.values())
            .collect(Collectors.toList());

        return dealerResults.stream()
            .map(result -> calculateDealerResult(dealer, players, result))
            .collect(Collectors.toList());
    }

    public int calculateDealerResult(final Participant dealer, final List<Participant> players,
        final Result result) {
        return (int) players.stream()
            .filter(player -> dealer.decideWinner(player).isSameResult(result))
            .count();
    }

    public Map<Name, Result> makePlayerResults(final List<Participant> players,
        final Participant dealer) {
        final Map<Name, Result> results = new LinkedHashMap<>();
        for (final Participant player : players) {
            final Result result = player.decideWinner(dealer);
            results.put(player.getName(), result);
        }
        return results;
    }

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
