package blackjack.card.domain;

import java.util.Arrays;

// todo: 테스트코드 수정
public enum GameResult {
	WIN(new GamblerWinStrategy(), "승"),
	DRAW(new DrawStrategy(), "무"),
	LOSE(new GamblerLoseStrategy(), "패");

	private final GameResultStrategy gameResultStrategy;
	private final String message;

	GameResult(GameResultStrategy gameResultStrategy, String message) {
		this.gameResultStrategy = gameResultStrategy;
		this.message = message;
	}

	public static GameResult createGameResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		return Arrays.stream(values())
			.filter(gameResult -> gameResult.gameResultStrategy.isResult(dealerCardBundle, gamblerCardBundle))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("승패가 지정되지 않았습니다."));
	}

	public String getMessage() {
		return message;
	}
}
