package domain;

import domain.card.Cards;
import domain.user.User;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public enum ResultType {
	BLACKJACK_WIN(
		scoreGap -> scoreGap > 0,
		cards -> cards.isInitialSize() && cards.getPoint() == 21,
		money -> money.doubleValue() * 1.5
	),
	WIN(
		scoreGap -> scoreGap > 0,
		Integer::doubleValue
	),
	DRAW(
		scoreGap -> scoreGap == 0,
		cards -> cards.getPoint() != 0,
		money -> money.doubleValue() * 0
	),
	LOSE(
		scoreGap -> scoreGap <= 0,
		money -> money.doubleValue() * -1
	);

	private final Predicate<Integer> resultJudge;
	private final Predicate<Cards> blackjackOrBurstJudge;
	private final Function<Integer, Double> getPrize;

	ResultType(Predicate<Integer> resultJudge, Function<Integer, Double> getPrize) {
		this(resultJudge, cards -> true, getPrize);
	}

	ResultType(Predicate<Integer> resultJudge, Predicate<Cards> blackjackOrBurstJudge, Function<Integer, Double> getPrize) {
		this.resultJudge = resultJudge;
		this.blackjackOrBurstJudge = blackjackOrBurstJudge;
		this.getPrize = getPrize;
	}

	public static ResultType from(User result, User compared) {
		return Arrays.stream(ResultType.values())
			.filter(type -> type.resultJudge.test(result.getScoreMinusBy(compared)))
			.filter(type -> type.blackjackOrBurstJudge.test(result.openAllCards()))
			.findFirst()
			.orElseThrow(NullPointerException::new);
	}

	public double getProfit(int bettingMoney) {
		return getPrize.apply(bettingMoney);
	}
}
