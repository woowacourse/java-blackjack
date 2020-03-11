package blackjack.player.card;

import java.util.Arrays;

public enum GameResult {
	WIN(1),
	DRAW(0),
	LOSE(-1);

	private final int result;

	GameResult(int result) {
		this.result = result;
	}

	public static GameResult findByResult(int result) {
		return Arrays.stream(values())
			.filter(gameResult -> gameResult.result == result)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(String.format("%d 는 존재하지 않는 결과 값 입니다.", result)));
	}
}
