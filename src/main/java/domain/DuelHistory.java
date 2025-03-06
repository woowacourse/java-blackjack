package domain;

public class DuelHistory {

	private int winCount;
	private int loseCount;
	private int drawCount;

	public DuelHistory() {
		this.drawCount = 0;
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

	public int getDrawCount() {
		return 0;
	}
}
