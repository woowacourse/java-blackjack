package blackjack.domain.result;

import blackjack.domain.card.Hand;
import blackjack.domain.result.exceptions.ResultTypeException;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum ResultType {
	BLACKJACK_WIN(1.5, "승", Hand::isBlackjackWin),
	WIN(1, "승", Hand::isWinWithoutBlackjack),
	LOSE(-1, "패", Hand::isLose),
	DRAW(0, "무", Hand::isDraw);

	private final double multiple;
	private final String resultString;
	private final BiFunction<Hand, Hand, Boolean> isTypeOf;

	ResultType(double multiple, String resultString, BiFunction<Hand, Hand, Boolean> isTypeOf) {
		this.multiple = multiple;
		this.resultString = resultString;
		this.isTypeOf = isTypeOf;
	}

	public static ResultType getPlayerResultByDealer(Hand player, Hand dealer) {
		return Arrays.stream(values())
				.filter(resultType -> resultType.isTypeOf.apply(player, dealer))
				.findFirst()
				.orElseThrow(() -> new ResultTypeException("ResultType 값을 찾을 수 없습니다."));
	}

	public double computeResultAmount(double amount) {
		return amount * multiple;
	}

	public boolean isWinOrBlackjackWin() {
		return this == BLACKJACK_WIN || this == WIN;
	}

	public boolean isLose() {
		return this == LOSE;
	}

	public boolean isDraw() {
		return this == DRAW;
	}

	public String getResultString() {
		return resultString;
	}
}
