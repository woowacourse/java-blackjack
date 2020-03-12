package domain;

public class Result {
	private final Name name;
	private final Record record;

	public Result(String name, int winCount, int loseCount) {
		this.name = new Name(name);
		this.record = new Record(winCount, loseCount);
	}

	public String getName() {
		return name.getValue();
	}

	public int getWinCount() {
		return record.getWinCount();
	}

	public int getLoseCount() {
		return record.getLoseCount();
	}

	public boolean hasMany() {
		return record.hasMany();
	}

	public boolean hasWin() {
		return record.hasWin();
	}
}
