package blackjack.domain.betting;

import blackjack.domain.betting.exceptions.MoniesException;
import blackjack.domain.user.Name;
import blackjack.domain.user.Players;
import blackjack.domain.user.Results;

import java.util.*;

import static java.util.stream.IntStream.range;


public final class Monies {
	private final Map<Name, Money> monies;

	private Monies(Map<Name, Money> monies) {
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

	public Monies computeGameResultMonies(Results results) {
		Map<Name, Money> map = new LinkedHashMap<>();
		for (Name name : monies.keySet()) {
			map.put(name, monies.get(name).computeResultingAmount(results.getResult(name)));
		}
		return new Monies(map);
	}

	public Money sumWithMinus() {
		Money sum = sum();
		return sum.minus();
	}

	private Money sum() {
		Money sum = Money.zero();
		for (Money money : monies.values()) {
			sum = sum.add(money);
		}
		return sum;
	}

	public Set<Name> keySet() {
		return monies.keySet();
	}

	public Money getMoney(Name name) {
		return monies.get(name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Monies monies1 = (Monies) o;
		return Objects.equals(monies, monies1.monies);
	}

	@Override
	public int hashCode() {
		return Objects.hash(monies);
	}

	@Override
	public String toString() {
		return "Monies{" +
				"monies=" + monies +
				'}';
	}
}
