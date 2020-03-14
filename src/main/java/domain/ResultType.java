package domain;

import domain.user.User;

import java.util.Arrays;
import java.util.function.Predicate;

public enum ResultType {
	WIN("승", scoreGap -> scoreGap > 0),
	DRAW("무", scoreGap -> scoreGap == 0),
	LOSE("패", scoreGap -> scoreGap < 0);

	private final String name;
	private final Predicate<Integer> resultJudge;

	ResultType(String name, Predicate<Integer> resultJudge) {
		this.name = name;
		this.resultJudge = resultJudge;
	}

	public static ResultType from(User result, User compared) {
		return Arrays.stream(ResultType.values())
			.filter(type -> type.resultJudge.test(result.getScoreMinusBy(compared)))
			.findFirst()
			.orElseThrow(NullPointerException::new);
	}

	public String getName() {
		return name;
	}
}
