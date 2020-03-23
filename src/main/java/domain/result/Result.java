package domain.result;

import java.util.Arrays;

import domain.gamer.Gamer;
import domain.gamer.Money;

public enum Result {
	BLACK_JACK("블랙잭", 0.5, new BlackjackResult()),
	WIN("승", 1, new WinResult()),
	DRAW("무", 0, new DrawResult()),
	LOSE("패", -1, (gamer, otherGamer) -> gamer.isBust() || gamer.getScore().isLowerThan(otherGamer.getScore()));

	private final String result;
	private final double times;
	private final ResultPolicy resultPolicy;

	Result(String result, double times, ResultPolicy resultPolicy) {
		this.result = result;
		this.times = times;
		this.resultPolicy = resultPolicy;
	}

	public static Result of(Gamer gamer, Gamer otherGamer) {
		return Arrays.stream(Result.values())
			.filter(result -> result.resultPolicy.compare(gamer, otherGamer))
			.findFirst()
			.orElseThrow(() -> new AssertionError("ResultType 중 반드시 하나가 반환되어야합니다."));
	}

	public double calculate(Money money) {
		return money.multiply(this.times);
	}

	public String getResult() {
		return this.result;
	}
}

