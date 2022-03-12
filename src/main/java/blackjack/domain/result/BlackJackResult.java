package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

import blackjack.domain.card.Cards;

public enum BlackJackResult {

	WIN("승", (player, dealer) ->
		(player.isBlackJack() && !dealer.isBlackJack()) ||
			(!player.isBust() && dealer.isBust()) ||
			(!player.isBust() && !dealer.isBust() && player.isGreaterThan(dealer))
	),
	LOSE("패", (player, dealer) ->
		player.isBust() ||
			(!player.isBlackJack() && dealer.isBlackJack()) ||
			(!player.isBust() && !dealer.isBust() && dealer.isGreaterThan(player))
	),
	DRAW("무", (player, dealer) ->
		(player.isBlackJack()) && dealer.isBlackJack() ||
			(!player.isBlackJack() && !dealer.isBlackJack() && player.isSame(dealer))
	);

	private static final String NOT_EXIST_ERROR = "옯바른 결과를 찾을 수 없습니다.";

	private final String value;
	private final BiPredicate<Cards, Cards> predicate;

	BlackJackResult(String value, BiPredicate<Cards, Cards> predicate) {
		this.value = value;
		this.predicate = predicate;
	}

	public static BlackJackResult of(Cards cards, Cards otherCards) {
		return Arrays.stream(values())
			.filter(result -> result.predicate.test(cards, otherCards))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_ERROR));
	}

	public BlackJackResult getReverse() {
		if (this == WIN) {
			return LOSE;
		}
		if (this == LOSE) {
			return WIN;
		}
		return DRAW;
	}

	public String getValue() {
		return value;
	}
}
