package domain.card;

public enum Rank {

	ACE("A", 1),
	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("J", 10),
	QUEEN("Q", 10),
	KNIGHT("K", 10);

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
		return this == ACE;
	}
}
