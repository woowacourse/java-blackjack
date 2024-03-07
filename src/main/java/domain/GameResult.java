package domain;

public enum GameResult {
	WIN,
	LOSE;

	public GameResult reverse() {
		if (this.equals(GameResult.WIN)) {
			return GameResult.LOSE;
		}
		return GameResult.WIN;
	}
}
