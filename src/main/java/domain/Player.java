package domain;

public class Player implements Gamer {
	private static final int BUST_NUMBER = 21;

	private String name;
	private Hands hands;

	public Player(String name) {
		this.name = name;
		this.hands = new Hands();
	}

	@Override
	public void add(Card card) {
		hands.add(card);
	}

	@Override
	public boolean isBust() {
		return hands.calculateTotalScore() > BUST_NUMBER;
	}

	@Override
	public int scoreHands() {
		return 0;
	}
}
