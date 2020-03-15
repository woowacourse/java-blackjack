package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

import blackjack.domain.user.User;

public enum ResultType {
	WIN((targetUser, compareUser) -> targetUser.compareTo(compareUser) > 0, "승"),
	DRAW((targetUser, compareUser) -> targetUser.compareTo(compareUser) == 0, "무"),
	LOSE((targetUser, compareUser) -> targetUser.compareTo(compareUser) < 0, "패");

	private final BiPredicate<User, User> judgementStandard;
	private final String alias;

	ResultType(BiPredicate<User, User> judgementStandard, String alias) {
		this.judgementStandard = judgementStandard;
		this.alias = alias;
	}

	public static ResultType of(User targetUser, User compareUser) {
		return Arrays.stream(values())
			.filter(resultType -> resultType.judgementStandard.test(targetUser, compareUser))
			.findFirst()
			.orElseThrow(() -> new NullPointerException("유효하지 않은 결과 타입입니다."));
	}

	public String getAlias() {
		return alias;
	}
}
