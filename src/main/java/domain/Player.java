package domain;

public class Player {
	private static final int BUST_NUMBER = 21;

	private String name;
	private Hands hands;

	public Player(String name) {
		this.name = name;
		this.hands = new Hands();
	}

	public void add(Card card) {
		hands.add(card);
	}

	public boolean isBust() {
		return hands.calculateTotalScore() > BUST_NUMBER;
	}
}
