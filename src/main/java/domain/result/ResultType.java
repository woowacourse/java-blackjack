package domain.result;

import domain.user.User;

import java.util.Arrays;
import java.util.function.Predicate;

public enum ResultType {
	WIN(value -> value > 0, "승"),
	DRAW( value -> value == 0, "무"),
	LOSE(value -> value < 0, "패" );

	private static final String CAN_NOT_FIND_DESIRABLE_RESULT_EXCEPTION_MESSAGE = "원하는 결과를 찾을 수 없습니다.";

	private final Predicate<Integer> resultJudge;
	private final String outputContent;

	ResultType(Predicate<Integer> resultJudge, String outputContent) {
		this.resultJudge = resultJudge;
		this.outputContent = outputContent;
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

	public static ResultType from(User user, User comparingUser) {
		return Arrays.stream(ResultType.values())
				.filter(type -> type.resultJudge.test(user.compareTo(comparingUser)))
				.findFirst()
				.orElseThrow(() -> new NullPointerException(CAN_NOT_FIND_DESIRABLE_RESULT_EXCEPTION_MESSAGE));
	}

	public String getOutputContent() {
		return outputContent;
	}
}
