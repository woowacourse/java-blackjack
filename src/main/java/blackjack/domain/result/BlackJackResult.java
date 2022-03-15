package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

import blackjack.domain.card.Cards;

public enum BlackJackResult {
	
	BLACKJACK_WIN("블랙잭", 1.5, (player, dealer) ->
		player.isBlackJack() && !dealer.isBlackJack()
	),
	WIN("승", 1, (player, dealer) ->
		(!player.isBust() && dealer.isBust()) ||
			(!player.isBust() && !dealer.isBust() && player.isGreaterThan(dealer))
	),
	LOSE("패", -1, (player, dealer) ->
		player.isBust() ||
			(!player.isBlackJack() && dealer.isBlackJack()) ||
			(!player.isBust() && !dealer.isBust() && dealer.isGreaterThan(player))
	),
	DRAW("무", 0, (player, dealer) ->
		(player.isBlackJack()) && dealer.isBlackJack() ||
			(!player.isBlackJack() && !dealer.isBlackJack() && player.isSame(dealer))
	);

	private static final String NOT_EXIST_ERROR = "옯바른 결과를 찾을 수 없습니다.";

	private final String name;
	private final double profit;
	private final BiPredicate<Cards, Cards> predicate;

	BlackJackResult(String value, double profit, BiPredicate<Cards, Cards> predicate) {
		this.name = value;
		this.profit = profit;
		this.predicate = predicate;
	}

	public static BlackJackResult of(Cards cards, Cards otherCards) {
		return Arrays.stream(values())
			.filter(result -> result.predicate.test(cards, otherCards))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_ERROR));
	}

	public double calculateEarning(int bet) {
		return bet * profit;
	}

	public double getReverseEarning(double earning) {
		return LOSE.getProfit() * earning;
	}

	public String getName() {
		return name;
	}

	public double getProfit() {
		return profit;
	}
}
