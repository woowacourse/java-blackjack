package domain;

public class Result {
	private static final int ONE = 1;

	private String name;
	private int winCount;
	private int loseCount;

	public Result(String name, int winCount, int loseCount) {
		this.name = name;
		this.winCount = winCount;
		this.loseCount = loseCount;
	}

	public void addWinCount() {
		winCount++;
	}

	public void addLoseCount() {
		loseCount++;
	}

	public String getName() {
		return name;
	}

	public int getWinCount() {
		return winCount;
	}

	public int getLoseCount() {
		return loseCount;
	}

	public boolean isPlayCountMoreThanOne() {
		return winCount + loseCount > ONE;
	}

	public boolean hasWin() {
		return winCount >= ONE;
	}
}
