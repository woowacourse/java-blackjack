package blackjack.card.domain;

import java.util.Arrays;

// todo: 테스트코드 수정
public enum GameResult {
	BLACKJACK_WIN(new GamblerBlackJackWinStrategy(), 1.5d),
	WIN(new GamblerWinStrategy(), 1.0d),
	DRAW(new DrawStrategy(), 0d),
	LOSE(new GamblerLoseStrategy(), -1d);

	private final GameResultStrategy gameResultStrategy;
	private final Double rate;

	GameResult(GameResultStrategy gameResultStrategy, Double rate) {
		this.gameResultStrategy = gameResultStrategy;
		this.rate = rate;
	}

	public static GameResult createGameResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		return Arrays.stream(values())
			.filter(gameResult -> gameResult.gameResultStrategy.isResult(dealerCardBundle, gamblerCardBundle))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("승패가 지정되지 않았습니다."));
	}

	public double getRate() {
		return rate;
	}
}


