package blackjack.domain.result;

import blackjack.domain.user.hand.Score;

public enum ResultType {
	WIN("승"),
	DRAW("무"),
	LOSE("패");

	private final String type;

	ResultType(String type) {
		this.type = type;
	}

	public static ResultType from(Score dealerScore, Score playerScore) {
		if (dealerScore.isMoreThan(playerScore)) {
			return LOSE;
		}
		if (playerScore.isMoreThan(dealerScore)) {
			return WIN;
		}
		return DRAW;
	}
}
