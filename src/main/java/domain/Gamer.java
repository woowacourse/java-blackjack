package domain;

public interface Gamer {
	boolean isBust();

	void add(Card card);

	int scoreHands();
}
