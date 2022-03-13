package domain.card;

public enum Rank {

	RANK_ACE("A", 1),
	RANK_TWO("2", 2),
	RANK_THREE("3", 3),
	RANK_FOUR("4", 4),
	RANK_FIVE("5", 5),
	RANK_SIX("6", 6),
	RANK_SEVEN("7", 7),
	RANK_EIGHT("8", 8),
	RANK_NINE("9", 9),
	RANK_TEN("10", 10),
	RANK_JACK("J", 10),
	RANK_QUEEN("Q", 10),
	RANK_KNIGHT("K", 10);

	private final String rank;
	private final int point;

	Rank(String rank, int point) {
		this.rank = rank;
		this.point = point;
	}

	public String getRank() {
		return rank;
	}

	public int getPoint() {
		return point;
	}

	public boolean isAce() {
		return this == RANK_ACE;
	}
}
