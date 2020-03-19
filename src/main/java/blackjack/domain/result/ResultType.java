package blackjack.domain.result;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;

import blackjack.domain.exceptions.InvalidResultTypeException;

// TODO: 2020-03-18 Player에 대해서, Player vs Dealer
public enum ResultType {
	BLACKJACK_WIN(ResultType::isBlackjackWin,
		bettingMoney -> bettingMoney.multiply(1.5)),
	WIN(ResultType::isWin,
		(playerResultScore, dealerResultScore) -> playerResultScore.compareTo(dealerResultScore) > 0,
		bettingMoney -> bettingMoney.multiply(1.0)),
	DRAW(ResultType::isDraw,
		(playerResultScore, dealerResultScore) -> playerResultScore.compareTo(dealerResultScore) == 0,
		bettingMoney -> bettingMoney.multiply(0.0)),
	LOSE(ResultType::isLose,
		(playerResultScore, dealerResultScore) -> playerResultScore.compareTo(dealerResultScore) < 0,
		bettingMoney -> bettingMoney.multiply(-1.0));

	private final BiPredicate<ResultScore, ResultScore> judgeByScoreType;
	private final BiPredicate<ResultScore, ResultScore> judgeByCompare;
	private final Function<BettingMoney, BettingMoney> calculateProfit;

	ResultType(BiPredicate<ResultScore, ResultScore> judgeByScoreType,
		BiPredicate<ResultScore, ResultScore> judgeByCompare,
		Function<BettingMoney, BettingMoney> calculateProfit) {
		this.judgeByScoreType = judgeByScoreType;
		this.judgeByCompare = judgeByCompare;
		this.calculateProfit = calculateProfit;
	}

	ResultType(BiPredicate<ResultScore, ResultScore> judgeByScoreType,
		Function<BettingMoney, BettingMoney> calculateProfit) {
		this(judgeByScoreType, (playerResultScore, dealerResultScore) -> false, calculateProfit);
	}

	public static ResultType from(ResultScore playerResultScore, ResultScore dealerResultScore) {
		validate(playerResultScore, dealerResultScore);
		return Arrays.stream(values())
			.filter(value -> value.judgeByScoreType.test(playerResultScore, dealerResultScore))
			.findFirst()
			.orElse(compareResultScoreFrom(playerResultScore, dealerResultScore));
	}

	private static void validate(ResultScore playerResultScore, ResultScore dealerResultScore) {
		if (Objects.isNull(playerResultScore) || Objects.isNull(dealerResultScore)) {
			throw new InvalidResultTypeException(InvalidResultTypeException.NULL);
		}
	}

	public BettingMoney calculateProfitFrom(BettingMoney bettingMoney) {
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
