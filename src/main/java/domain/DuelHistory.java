package domain;

public class DuelHistory {
	private static final int WIN = 1;
	private static final int DRAW = 0;
	private static final int LOSE = -1;

	private int winCount;
	private int loseCount;
	private int drawCount;

	public DuelHistory() {
		this.drawCount = 0;
		this.winCount = 0;
		this.loseCount = 0;
	}

	public void write(final int duelResult) {
		if (duelResult >= WIN) {
			winCount++;
		}
		if (duelResult == DRAW) {
			drawCount++;
		}
		if (duelResult <= LOSE) {
			loseCount++;
		}
	}

	public int getWinCount() {
		return winCount;
	}

	public int getLoseCount() {
		return loseCount;
	}

	public int getDrawCount() {
		return drawCount;
	}
}
