package domain;

public class Record {
	private static final int MANY_TIMES = 2;
	private static final int MIN_COUNT = 0;

	private final int winCount;
	private final int loseCount;

	public Record(int winCount, int loseCount) {
		validate(winCount, loseCount);
		this.winCount = winCount;
		this.loseCount = loseCount;
	}

	private void validate(int winCount, int loseCount) {
		if (winCount < MIN_COUNT || loseCount < MIN_COUNT) {
			throw new IllegalArgumentException("winCount, loseCount는 0 이상이어야 합니다.");
		}
	}

	public int getWinCount() {
		return winCount;
	}

	public int getLoseCount() {
		return loseCount;
	}


	public boolean hasMany() {
		return winCount + loseCount >= MANY_TIMES;
	}

	public boolean hasWin() {
		return winCount != 0;
	}
}
