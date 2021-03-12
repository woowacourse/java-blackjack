package blakcjack.domain.outcome;

import blakcjack.domain.money.Money;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;

import java.util.*;

public class OutcomeStatistics {
	private final Map<Participant, Money> participantsProfit = new LinkedHashMap<>();

	public OutcomeStatistics(final Dealer dealer, final List<Player> players) {
		Map<Player, Money> playersProfit = getPlayersProfit(dealer, players);
		participantsProfit.put(dealer, aggregateDealerProfitFrom(playersProfit));
		participantsProfit.putAll(playersProfit);
	}

	private Map<Player, Money> getPlayersProfit(final Dealer dealer, final List<Player> players) {
		final Map<Player, Money> playersProfit = new LinkedHashMap<>();
		for (final Player player : players) {
			final Outcome playerOutcome = Outcome.of(dealer, player);
			final Money playerProfit = player.calculateProfit(playerOutcome);
			playersProfit.put(player, playerProfit);
		}
		return playersProfit;
	}

	private Money aggregateDealerProfitFrom(final Map<Player, Money> playersProfit) {
		Collection<Money> playersProfitValues = playersProfit.values();
		return Money.calculateDealerProfitFrom(playersProfitValues);
	}

	public Map<Participant, Money> getParticipantsProfit() {
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
