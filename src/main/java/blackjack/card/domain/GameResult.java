package blackjack.card.domain;

import java.util.Arrays;

public enum GameResult {
	WIN(1, "승"),
	DRAW(0, "무"),
	LOSE(-1, "패");

	private final int result;
	private final String message;

	GameResult(int result, String message) {
		this.result = result;
		this.message = message;
	}

	private static GameResult findByResult(int result) {
		return Arrays.stream(values())
			.filter(gameResult -> gameResult.result == result)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(String.format("%d 는 존재하지 않는 결과 값 입니다.", result)));
	}

	public static GameResult createGameResult(int gamblerScore, int dealerScore) {
		int compare = Integer.compare(gamblerScore, dealerScore);
		return findByResult(compare);
	}

	public String getMessage() {
		return message;
	}
}
