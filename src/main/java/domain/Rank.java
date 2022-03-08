package domain;

public enum Rank {

	RANK_A('A', 1), RANK_2('2', 2), RANK_3('3', 3),
	RANK_4('4', 4), RANK_5('5', 5), RANK_6('6', 6),
	RANK_7('7', 7), RANK_8('8', 8), RANK_9('9', 9),
	RANK_J('J', 10), RANK_Q('Q', 10), RANK_K('K', 10),
	;

	private final char rank;
	private final int point;

	Rank(char rank, int point) {
		this.rank = rank;
		this.point = point;
	}

	public char getRank() {
		return rank;
	}

	public int getPoint() {
		return point;
	}
}
