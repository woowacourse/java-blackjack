package blackjack.domain.betting;

import blackjack.domain.betting.exceptions.BettingMoniesException;
import blackjack.domain.user.Playable;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.LongStream.range;


public class BettingMonies {
	private final Map<Playable, BettingMoney> bettingMonies;

	private BettingMonies(Map<Playable, BettingMoney> bettingMonies) {
		this.bettingMonies = bettingMonies;
	}

	public static BettingMonies of(Players players, List<BettingMoney> bettingMonies) {
		validateParamsAreNotNull(players, bettingMonies);
		validateParamSizesAreSame(players, bettingMonies);

		Map<Playable, BettingMoney> map = new LinkedHashMap<>();
		range(0, players.memberSize())
				.forEach((i) -> map.put(players.getPlayers().get((int) i), bettingMonies.get((int) i)));
		return new BettingMonies(map);
	}

	private static void validateParamsAreNotNull(Players players, List<BettingMoney> bettingMonies) {
		if (players == null || bettingMonies == null) {
			throw new BettingMoniesException("Null 값이 매개변수로 들어왔습니다.");
		}
	}

	private static void validateParamSizesAreSame(Players players, List<BettingMoney> bettingMonies) {
		if (players.memberSize() != bettingMonies.size()) {
			throw new BettingMoniesException("플레이어의 수와 배팅 금액의 수가 다릅니다.");
		}
	}
}
