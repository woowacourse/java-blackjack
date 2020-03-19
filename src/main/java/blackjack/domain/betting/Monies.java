package blackjack.domain.betting;

import blackjack.domain.betting.exceptions.MoniesException;
import blackjack.domain.user.Name;
import blackjack.domain.user.Players;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.IntStream.range;


public final class Monies {
	private final Map<Name, Money> monies;

	public Monies(Map<Name, Money> monies) {
		this.monies = monies;
	}

	public static Monies of(Players players, List<Money> bettingMonies) {
		validateParamsAreNotNull(players, bettingMonies);
		validateParamSizesAreSame(players, bettingMonies);

		Map<Name, Money> map = new LinkedHashMap<>();
		range(0, players.memberSize())
				.forEach((i) -> map.put(players.getPlayers().get(i).getName(), bettingMonies.get(i)));

		return new Monies(map);
	}

	public static Monies of(MoniesFactory moniesFactory) {
		return moniesFactory.create();
	}

	private static void validateParamsAreNotNull(Players players, List<Money> bettingMonies) {
		if (players == null || bettingMonies == null) {
			throw new MoniesException("Null 값이 매개변수로 들어왔습니다.");
		}
	}

	private static void validateParamSizesAreSame(Players players, List<Money> bettingMonies) {
		if (players.memberSize() != bettingMonies.size()) {
			throw new MoniesException("플레이어의 수와 배팅 금액의 수가 다릅니다.");
		}
	}

	public Set<Name> keySet() {
		return monies.keySet();
	}

	public Money getMoney(Name name) {
		return monies.get(name);
	}
}
