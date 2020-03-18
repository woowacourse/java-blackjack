package domain.result;

import domain.user.User;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.DoubleUnaryOperator;

public enum ResultType {
	BLACKJACK_WIN(true, (user, other) ->
			user.isBlackjack() && other.isNotBlackjack(),
			bettingMoney -> bettingMoney * 1.5),
	WIN(false, (user, other) ->
			user.isNotBlackjack() && user.compareTo(other) > 0,
			bettingMoney -> bettingMoney * 1),
	DRAW(false, (user, other) ->
			(user.isBlackjack() && other.isBlackjack()) || (user.isNotBurst() && other.isNotBurst() && user.compareTo(other) == 0),
			bettingMoney -> 0),
	LOSE(false, (user, other) ->
			user.isBurst() || user.compareTo(other) < 0,
			bettingMoney -> bettingMoney * -1 );

	private static final String CAN_NOT_FIND_DESIRABLE_RESULT_EXCEPTION_MESSAGE = "원하는 결과를 찾을 수 없습니다.";

	private final Boolean blackjack;
	private final BiFunction<User, User, Boolean> resultJudge;
	private final DoubleUnaryOperator bettingMoneyExchanger;

	ResultType(Boolean blackjack, BiFunction<User, User, Boolean> resultJudge, DoubleUnaryOperator bettingMoneyExchanger) {
		this.blackjack = blackjack;
		this.resultJudge = resultJudge;
		this.bettingMoneyExchanger = bettingMoneyExchanger;
	}

	public static ResultType from(User user, User comparingUser) {
		return Arrays.stream(ResultType.values())
				.filter(type -> type.blackjack.equals(user.isBlackjack()))
				.filter(type -> type.resultJudge.apply(user, comparingUser))
				.findFirst()
				.orElseThrow(() -> new NullPointerException(CAN_NOT_FIND_DESIRABLE_RESULT_EXCEPTION_MESSAGE));
	}

	public Double getExchangedBettingMoney(Double bettingMoney) {
		return bettingMoneyExchanger.applyAsDouble(bettingMoney);
	}
}
