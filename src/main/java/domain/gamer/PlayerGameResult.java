package domain.gamer;

import domain.card.Hands;

/**
 *   Player의 승무패를 가지는 클래스
 *
 *   @author ParkDooWon
 */
public enum PlayerGameResult {
	WIN("승"),
	LOSE("패"),
	DRAW("무");

	private String result;

	PlayerGameResult(String result) {
		this.result = result;
	}

	public static PlayerGameResult matchResult(int dealerScore, int playerScore) {
		if (isDrawGame(dealerScore, playerScore)) {
			return PlayerGameResult.DRAW;
		}
		if (isDefeatGame(dealerScore, playerScore)) {
			return PlayerGameResult.LOSE;
		}
		return PlayerGameResult.WIN;
	}

	private static boolean isDrawGame(int dealerScore, int playerScore) {
		return (dealerScore == playerScore) && (dealerScore <= Hands.BLACKJACK);
	}

	private static boolean isDefeatGame(int dealerScore, int playerScore) {
		return ((playerScore > Hands.BLACKJACK) ||
			((dealerScore > playerScore) && (dealerScore <= Hands.BLACKJACK)));
	}

	public String getResult() {
		return result;
	}

	public boolean isLose() {
		return this.equals(PlayerGameResult.LOSE);
	}

	public boolean isWin() {
		return this.equals(PlayerGameResult.WIN);
	}

	public boolean isDraw() {
		return this.equals(PlayerGameResult.DRAW);
	}
}
