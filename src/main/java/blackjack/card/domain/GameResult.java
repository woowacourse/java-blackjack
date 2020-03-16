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

	public static GameResult findByComparing(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
		validate(gamblerCardBundle, dealerCardBundle);
		if (gamblerCardBundle.isBurst()) {
			return GameResult.LOSE;
		}
		if (dealerCardBundle.isBurst()) {
			return GameResult.WIN;
		}
		int result = Integer.compare(gamblerCardBundle.calculateScore(), dealerCardBundle.calculateScore());
		return findByResult(result);
	}

	private static void validate(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
		if (gamblerCardBundle == null) {
			throw new IllegalArgumentException("갬블러의 카드가 없습니다.");
		}
		if (dealerCardBundle == null) {
			throw new IllegalArgumentException("딜러의 카드가 없습니다.");
		}
	}

	private static GameResult findByResult(int result) {
		return Arrays.stream(values())
				.filter(gameResult -> gameResult.result == result)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("%d 는 존재하지 않는 결과 값 입니다.", result)));
	}

	public String getMessage() {
		return message;
	}
}
