package blakcjack.domain.outcome;

import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OutcomeStatistics {
	private final Map<Participant, Float> participantsProfit = new LinkedHashMap<>();

	public OutcomeStatistics(final Dealer dealer, final List<Player> players) {
		Map<Player, Float> playersProfit = getPlayersProfit(dealer, players);
		participantsProfit.put(dealer, aggregateDealerProfit(playersProfit));
		participantsProfit.putAll(playersProfit);
	}

	private Map<Player, Float> getPlayersProfit(final Dealer dealer, final List<Player> players) {
		final Map<Player, Float> playersProfit = new LinkedHashMap<>();
		for (final Player player : players) {
			final Outcome playerOutcome = dealer.judgeOutcomeOf(player);
			final float playerProfit = player.calculateProfit(playerOutcome);
			playersProfit.put(player, playerProfit);
		}
		return playersProfit;
	}

	private float aggregateDealerProfit(final Map<Player, Float> playersProfit) {
		float profit = 0f;
		for (final Player player : playersProfit.keySet()) {
			final float playerProfit = playersProfit.get(player);
			profit -= playerProfit;
		}
		return profit;
	}

	public Map<Participant, Float> getParticipantsProfit() {
		return participantsProfit;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final OutcomeStatistics that = (OutcomeStatistics) o;
		return Objects.equals(participantsProfit, that.participantsProfit);
	}

	@Override
	public int hashCode() {
		return Objects.hash(participantsProfit);
	}
}
