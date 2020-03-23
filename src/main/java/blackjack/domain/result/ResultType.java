package blackjack.domain.result;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;

import blackjack.domain.exceptions.InvalidResultTypeException;

public enum ResultType {
	BLACKJACK_WIN(ResultType::isBlackjackWin,
		bettingMoney -> bettingMoney.multiplyBy(1.5)),
	WIN(ResultType::isWin,
		(playerResultScore, dealerResultScore) -> playerResultScore.compareTo(dealerResultScore) > 0,
		bettingMoney -> bettingMoney.multiplyBy(1.0)),
	DRAW(ResultType::isDraw,
		(playerResultScore, dealerResultScore) -> playerResultScore.compareTo(dealerResultScore) == 0,
		bettingMoney -> bettingMoney.multiplyBy(0.0)),
	LOSE(ResultType::isLose,
		(playerResultScore, dealerResultScore) -> playerResultScore.compareTo(dealerResultScore) < 0,
		bettingMoney -> bettingMoney.multiplyBy(-1.0));

	private final BiPredicate<ResultScore, ResultScore> judgeByHandType;
	private final BiPredicate<ResultScore, ResultScore> judgeByCompare;
	private final Function<BettingMoney, Integer> calculateProfit;

	ResultType(BiPredicate<ResultScore, ResultScore> judgeByHandType,
		BiPredicate<ResultScore, ResultScore> judgeByCompare,
		Function<BettingMoney, Integer> calculateProfit) {
		this.judgeByHandType = judgeByHandType;
		this.judgeByCompare = judgeByCompare;
		this.calculateProfit = calculateProfit;
	}

	ResultType(BiPredicate<ResultScore, ResultScore> judgeByHandType,
		Function<BettingMoney, Integer> calculateProfit) {
		this(judgeByHandType, (playerResultScore, dealerResultScore) -> false, calculateProfit);
	}

	public static ResultType from(ResultScore playerResultScore, ResultScore dealerResultScore) {
		validate(playerResultScore, dealerResultScore);

		return Arrays.stream(values())
			.filter(value -> value.judgeByHandType.test(playerResultScore, dealerResultScore))
			.findFirst()
			.orElse(compareResultScoreFrom(playerResultScore, dealerResultScore));
	}

	private static void validate(ResultScore playerResultScore, ResultScore dealerResultScore) {
		if (Objects.isNull(playerResultScore) || Objects.isNull(dealerResultScore)) {
			throw new InvalidResultTypeException(InvalidResultTypeException.NULL);
		}
	}

	public Integer calculateProfitFrom(BettingMoney bettingMoney) {
		return this.calculateProfit.apply(bettingMoney);

	}

	private static ResultType compareResultScoreFrom(ResultScore playerResultScore, ResultScore dealerResultScore) {
		return Arrays.stream(values())
			.filter(value -> value.judgeByCompare.test(playerResultScore, dealerResultScore))
			.findFirst()
			.orElseThrow(() -> new InvalidResultTypeException(InvalidResultTypeException.NULL));
	}

	private static boolean isBlackjackWin(ResultScore playerResultScore, ResultScore dealerResultScore) {
		return playerResultScore.isBlackjack() && !dealerResultScore.isBlackjack();
	}

	private static boolean isWin(ResultScore playerResultScore, ResultScore dealerResultScore) {
		return playerResultScore.isNormal() && dealerResultScore.isBust();
	}

	private static boolean isDraw(ResultScore playerResultScore, ResultScore dealerResultScore) {
		return playerResultScore.isBlackjack() && dealerResultScore.isBlackjack();
	}

	private static boolean isLose(ResultScore playerResultScore, ResultScore dealerResultScore) {
		return (playerResultScore.isBust() ||
			!playerResultScore.isBlackjack() && dealerResultScore.isBlackjack());
	}
}
