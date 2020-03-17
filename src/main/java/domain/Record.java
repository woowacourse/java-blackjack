package domain;

public class Record {
	private static final int MANY_TIMES = 2;
	private static final int MIN_COUNT = 0;

	private final int winCount;
	private final int drawCount;
	private final int loseCount;

	public Record(int winCount, int drawCount, int loseCount) {
		validate(winCount, drawCount, loseCount);
		this.winCount = winCount;
		this.drawCount = drawCount;
		this.loseCount = loseCount;
	}

	private void validate(int winCount, int drawCount, int loseCount) {
		if (winCount < MIN_COUNT || drawCount < MIN_COUNT || loseCount < MIN_COUNT) {
			throw new IllegalArgumentException("winCount, drawCount, loseCount는 0 이상이어야 합니다.");
		}
	}

	public Record add(Record record) {
		return new Record(
				winCount + record.winCount,
				drawCount + record.drawCount,
				loseCount + record.loseCount
		);
	}

	public int getWinCount() {
		return winCount;
	}

	public int getDrawCount() {
		return drawCount;
	}

	public int getLoseCount() {
		return loseCount;
	}

	public boolean hasMany() {
		return winCount + drawCount + loseCount >= MANY_TIMES;
	}

	public boolean hasWin() {
		return winCount != 0;
	}

	public boolean hasDraw() {
		return drawCount != 0;
	}
}
