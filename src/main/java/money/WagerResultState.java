package money;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import duel.DuelResult;

public enum WagerResultState {
	BLACKJACK_WAGER_WIN(2.5, (result, isBlackjack) -> result == DuelResult.WIN && isBlackjack),
	WAGER_WIN(2, (result, isBlackjack) -> result == DuelResult.WIN),
	WAGER_DRAW(1, (result, isBlackjack) -> result == DuelResult.DRAW),
	WAGER_LOSE(0, (result, isBlackjack) -> result == DuelResult.LOSE);

	private final double wagerMultiplier;

	private final BiPredicate<DuelResult, Boolean> determineState;

	WagerResultState(final double wagerMultiplier, final BiPredicate<DuelResult, Boolean> determineState) {
		this.wagerMultiplier = wagerMultiplier;
		this.determineState = determineState;
	}

	public static Money calculateWagerResultMoney(
		final DuelResult duelResult,
		final boolean isBlackjack,
		final Money wagerMoney
	) {
		final WagerResultState result = sortByWagerMultiplier().stream()
			.filter(wagerResultState -> wagerResultState.determineState.test(duelResult, isBlackjack))
			.findFirst()
			.orElseThrow(IllegalStateException::new);
		return wagerMoney.multiply(result.wagerMultiplier);
	}

	private static List<WagerResultState> sortByWagerMultiplier() {
		return Arrays.stream(values())
			.sorted(Comparator.comparingDouble(WagerResultState::getWagerMultiplier).reversed())
			.collect(Collectors.toList());
	}

	private double getWagerMultiplier() {
		return wagerMultiplier;
	}
}
