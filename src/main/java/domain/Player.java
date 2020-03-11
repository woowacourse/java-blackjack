package domain;

/**
 *    게임 플레이어 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Player implements Gamer {
	private static final int BLACKJACK = 21;

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
		return hands.calculateTotalScore() > BLACKJACK;
	}

	@Override
	public int scoreHands() {
		if (isBust() && hands.hasAce()) {

		}
		return 0;
	}

	@Override
	public boolean isBlackjack() {
		return hands.calculateTotalScore() == BLACKJACK;
	}
}
