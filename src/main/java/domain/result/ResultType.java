package domain.result;

import domain.user.User;

import java.util.Arrays;
import java.util.function.Predicate;

public enum ResultType {
	WIN("승", value -> value > 0),
	DRAW("무", value -> value == 0),
	LOSE("패", value -> value < 0);

	private final String name;
	private final Predicate<Integer> resultJudge;

	ResultType(String name, Predicate<Integer> resultJudge) {
		this.name = name;
		this.resultJudge = resultJudge;
	}

	public static ResultType opposite(ResultType resultType) {
		if (WIN.equals(resultType)) {
			return LOSE;
		}
		if (LOSE.equals(resultType)) {
			return WIN;
		}
		return DRAW;
	}

	public String getName() {
		return name;
	}

	public static ResultType from(User result, User compared) {
		return Arrays.stream(ResultType.values())
				.filter(type -> type.resultJudge.test(result.compareTo(compared)))
				.findFirst()
				.orElseThrow(NullPointerException::new);
	}
}
