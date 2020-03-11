package domain.result;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public enum ResultType {
	WIN("승"),
	DRAW("무"),
	LOSE("패");

	private final String name;

	ResultType(String name) {
		this.name = name;
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
}
