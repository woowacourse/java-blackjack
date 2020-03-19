package domain;

public class Record {
	public static final Record NONE = new Record(0, 0, 0);
	private static final Record WIN = new Record(1, 0, 0);
	private static final Record DRAW = new Record(0, 1, 0);
	private static final Record LOSE = new Record(0, 0, 1);
	private static final int MANY_TIMES = 2;

	private final int winCount;
	private final int drawCount;
	private final int loseCount;

	private Record(int winCount, int drawCount, int loseCount) {
		this.winCount = winCount;
		this.drawCount = drawCount;
		this.loseCount = loseCount;
	}

	public static Record of(User source, User target) {
		if (source.isWin(target)) {
			return WIN;
		}
		if (source.isDraw(target)) {
			return DRAW;
		}
		return LOSE;
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
