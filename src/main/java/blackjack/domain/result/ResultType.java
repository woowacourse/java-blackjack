package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.Predicate;

import blackjack.domain.user.User;

public enum ResultType {
	WIN(value -> value > 0, "승"),
	DRAW(value -> value == 0, "무"),
	LOSE(value -> value < 0, "패");

	private Predicate<Integer> compareResult;
	private final String alias;

	ResultType(Predicate<Integer> compareResult, String alias) {
		this.compareResult = compareResult;
		this.alias = alias;
	}

	public static ResultType from(User targetUser, User compareUser) {
		return Arrays.stream(values())
			.filter(resultType -> resultType.compareResult
				.test(Integer.compare(targetUser.getBustHandledScore(), compareUser.getBustHandledScore())))
			.findAny()
			.orElseThrow(() -> new NullPointerException("유효하지 않은 결과 타입입니다."));
	}

	public String getAlias() {
		return alias;
	}
}
