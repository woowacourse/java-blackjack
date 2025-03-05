package domain;

public class DuelHistory {

	private int winCount;
	private int loseCount;

	public DuelHistory() {
		this.winCount = 0;
		this.loseCount = 0;
	}

	public void write(boolean duelResult) {
		if (duelResult) {
			winCount++;
			return;
		}
		loseCount++;
	}

	public int getWinCount() {
		return winCount;
	}

	public int getLoseCount() {
		return loseCount;
	}
}
